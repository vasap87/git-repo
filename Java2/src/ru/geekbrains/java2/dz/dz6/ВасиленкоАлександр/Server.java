package ru.geekbrains.java2.dz.dz6.ВасиленкоАлександр;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

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
