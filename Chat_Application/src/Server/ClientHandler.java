package Server;

import DAOs.UserDAO;
import Models.User;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;;


public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private static final Lock lock = new ReentrantLock(); // Lock for concurrency control
    private UserDAO userDAO;

    public ClientHandler(Socket clientSocket, Connection connection) {
        this.clientSocket = clientSocket;
        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);
            userDAO = new UserDAO(connection); // Initialize UserDAO with the connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            //read username from client
            DataInputStream in = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream()));
            String username = in.readUTF();
            String status = in.readUTF();

            //String username = input.readLine(); // Read the username sent from the client
            User user = userDAO.getUserByUsername(username); // Fetch user from the database
            userDAO.updateUserStatus(username, status);  //update user status to active

            if (user == null) {
                // User does not exist, so register them
                user = new User(username, "ONLINE");
                userDAO.addUser(user);
                output.println("User registered: " + username);
            } else {
                // User exists, retrieve and send back relevant info
                output.println("Welcome back, " + username);
            }

            // Handle further communication with the client (chat, messages, etc.)
            String message;
            while ((message = input.readLine()) != null) {
                lock.lock();
                try {
                    System.out.println(username + ": " + message);
                    // Broadcast the message to other clients or handle it appropriately
                } finally {
                    lock.unlock(); // Release the lock
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
