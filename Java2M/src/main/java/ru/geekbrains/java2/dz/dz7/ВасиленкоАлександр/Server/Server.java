package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 */
public class Server {
    private static final int PORT = 8188;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Thread thread = new Thread(new ServerThread(serverSocket.accept()));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
