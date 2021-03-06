package vttp2022.day05.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

public class ClientMain {
    //this is a test to udpate

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        System.out.println("Connecting to localhost at port 3000");
        // Connect the server
        // 127.0.0.1 - localhost
        Socket sock = new Socket("127.0.0.1", 12345);
        System.out.println("Connected ...");

        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        ObjectInputStream ois = new ObjectInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        // Get input from user
        Console cons = System.console();
        String input = cons.readLine("Say something to the server ");

        // Write to server
        dos.writeUTF(input);
        dos.flush();

        List<String>response = (List<String>)ois.readObject();

        // Wait for response from server
        // while (true) {
        //     String response = dis.readUTF();
        //     if (response.equals("FINSHED"))
        //         break;
        //     System.out.printf(">> %s\n", response);
        // }


        // String response;
        // do{
        // response = dis.readUTF();
        // System.out.printf(">> %s\n", response);
        // } while(response !="FINISHED");

        // close the strams
        is.close();
        os.close();

        // close the socket
        sock.close();
    }

}
