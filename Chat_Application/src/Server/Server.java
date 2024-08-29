package Server;

import DAOs.DatabaseManager;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
    private static final int PORT = 5000; // Port number for the server
    private ExecutorService threadPool;

    public Server() {
        // A thread pool that dynamically expands based on need
        // It will create new threads as needed, and also reuse threads that have finished executing
        threadPool = Executors.newCachedThreadPool(); 
    }

    public void start() throws SQLException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat server started on port " + PORT);
            DatabaseManager dbManager = new DatabaseManager();
            Connection connection = dbManager.getConnection();

            while (true) {
                Socket clientSocket = serverSocket.accept(); // Accept new client connection
                System.out.println("New client connected: " + clientSocket.getInetAddress());
                
                // Create a new ClientHandler for the connected client
                ClientHandler clientHandler = new ClientHandler(clientSocket, connection);
                
                // Submit the client handler to the thread pool
                threadPool.submit(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        Server server = new Server();
        server.start();
    }
}
