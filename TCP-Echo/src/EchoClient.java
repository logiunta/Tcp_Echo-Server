import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 1500));
            System.out.println("Connection with server established");
            String message;
            String reply = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //buffer per leggere risposte dal server
            BufferedWriter writer = new BufferedWriter((new OutputStreamWriter(socket.getOutputStream()))); //buffer per scrivere al server
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in)); //buffer per leggere input da sistema
            while(!socket.isClosed()) {
                System.out.println("Write a message to send to the server");
                while (!(message = localReader.readLine()).equals("exit")) {
                    writer.write(message + "\r\n");
                    writer.flush();
                    reply = reader.readLine();
                    System.out.println("Server sent: " + reply);
                    System.out.println("Write a message to send to the server");

                }
            }

        }

        catch(UnknownHostException e){
            System.out.println("Server not Found");
        }
        catch(IOException e){
            System.out.println("A problem with connection occurred");
        }



    }
}
