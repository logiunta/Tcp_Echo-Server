import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(),1500));

            while(true){
                System.out.println(("Server waiting for clients"));
                String message;
                Socket client = serverSocket.accept();
                System.out.println("Connection with client "+client.getInetAddress()+" established");
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                while((message = reader.readLine()) != null){
                    System.out.println("Message from client: "+client.getInetAddress()+": "+message);
                    writer.write(message+"\r\n");
                    writer.flush();
                }
                if(!client.isClosed())
                    System.out.println("Connection with client "+client.getInetAddress()+" closed");

            }

        }
        catch(IOException e){
            System.out.println("Connection lost");
        }



    }
}
