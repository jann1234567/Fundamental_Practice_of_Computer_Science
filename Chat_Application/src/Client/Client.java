import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {

            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            // Wrap them in buffer reader and writer
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Read input from keyboards
            Scanner scanner = new Scanner(System.in);

            while(true) {

                String msgToSend = scanner.nextLine();

                // Sending message to the server
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                // Flush here meaning push to the output stream and sending message without waiting the buffer to be full
                // So that user doesn't receive a long message all at once
                bufferedWriter.flush();

                // Now we wait for the server to response to our sent message
                System.out.println("Server: " + bufferedReader.readLine());

                // If user types exit, then break the loop
                if (msgToSend.equalsIgnoreCase("exit")) {
                    if (scanner != null) {
                        scanner.close();
                    }
                    break;
                }
            
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}