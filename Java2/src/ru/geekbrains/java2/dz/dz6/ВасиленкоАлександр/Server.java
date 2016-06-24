package ru.geekbrains.java2.dz.dz6.ВасиленкоАлександр;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 */
public class Server {
    private static final String EXIT = "exit";
    private static final int PORT = 8188;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            try (Socket socket = serverSocket.accept()) {
//                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
//                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//                    out.println("Connected to server");
//
//                    while(true){
//                        String s = in.readLine();
//                        if (EXIT.contains(s.trim())) break;
//                        out.println(s);
//                    }
//                }
                try (Scanner in = new Scanner(socket.getInputStream())) {
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println("Connected to server");

                    while(true){
                        if(in.hasNextLine()) {
                            String s = in.nextLine();
                            if (EXIT.contains(s.trim())) break;
                            out.println(s);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
