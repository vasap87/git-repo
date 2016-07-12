package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class Server {
    public Server(){
        try{
            ServerSocket serverSocket = new ServerSocket(8899);
            while(true){
                Socket socket = serverSocket.accept();
                Thread serverThread = new Thread(new ServerThread(socket));
                serverThread.run();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
