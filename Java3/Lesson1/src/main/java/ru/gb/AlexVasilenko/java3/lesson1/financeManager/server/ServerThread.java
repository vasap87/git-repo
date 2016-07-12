package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server;

import java.io.*;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class ServerThread implements Runnable {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream objIN = new DataInputStream(socket.getInputStream());
            DataOutputStream objOUT = new DataOutputStream(socket.getOutputStream());
            while (true){
                String fromUser = objIN.readUTF();
                String parts[] = fromUser.split("\t");
                switch(parts[0]){
                    case "authorisation": {

                    }
                    case "registration" :{

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
