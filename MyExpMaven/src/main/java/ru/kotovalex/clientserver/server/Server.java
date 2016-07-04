package ru.kotovalex.clientserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 04.07.2016.
 */
public class Server {
    private static Server instance = new Server();

    public static Server getInstance() {
        return instance;
    }

    private Server() {
    }

    protected void start() {
        try {
            ServerSocket ss = new ServerSocket(8899);
            while(true) {
                Socket socket = ss.accept();
                Thread thread = new Thread(new SimpleThread(socket));
                thread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
