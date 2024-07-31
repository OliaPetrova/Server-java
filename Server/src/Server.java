import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    System.out.println("New connection accepted from port: " + clientSocket.getPort());


                    final String name = in.readLine();
                    System.out.println("Received message from client: " + name);

                    
                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                } catch (Exception e) {
                    System.out.println("Exception in handling client connection: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in server: " + e.getMessage());
        }
    }
}