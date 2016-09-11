package serialization;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 08.09.2016.
 */
public class Server {
    public static Server instance = new Server();

    private Server() {
    }

    public static Server getInstance() {
        return instance;
    }

    public void start() {
        try (ServerSocket ss = new ServerSocket(8899)) {
            Socket clientSocket = ss.accept();
            new ServerThread(clientSocket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server.getInstance().start();
    }


}
