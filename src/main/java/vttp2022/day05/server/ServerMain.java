package vttp2022.day05.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ServerMain {

    public static void main(String[] args)
            throws IOException {

        // Create a server socket and listen to a port
        ServerSocket server = new ServerSocket(12345);

        System.out.println("Waiting for connection on port 12345...");
        // Wait for incoming client connection
        Socket sock = server.accept();
        System.out.println("Connection accepted");

        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        ObjectOutputStream oos = new ObjectOutputStream(os);

        // Read request from client
        String request = dis.readUTF();
        String[] terms = request.split(" ");

        switch (terms[0]) {
            case ("cookie"):

                BufferedReader reader = new BufferedReader(
                        new FileReader("C:\\Users\\coyot\\Desktop\\cookie_file.txt"));
                String line;
                List<String>words = new LinkedList<>();
                while ((line = reader.readLine()) != null)
                {
                    words.add(line);
                    System.out.println(line);
                }
                dos.writeUTF("FINISHED");
                dos.flush();
                break;
        }

        // System.out.printf("Received request: %s\n", request);

        // // Perform some operation on the request
        // request = "From the server: " + request.toUpperCase();

        // // Write back the data to the client

        // close the strams
        is.close();
        os.close();

        // close the socket
        sock.close();
    }
}
