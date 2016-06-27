package ru.geekbrains.java2.dz.dz6.ВасиленкоАлександр;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 27.06.2016.
 */
public class ServerThread implements Runnable {
    private Socket socket;
    private static final String EXIT = "exit";

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Connected to server");
            while (true) {
                if (in.hasNextLine()) {
                    String s = in.nextLine();
                    if (EXIT.contains(s.trim())) break;
                    out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблема с входным потоком на стороне потока сервера");
        } finally {
            if (in != null && out != null) {
                in.close();
                out.close();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Проблема с закрытием сокета на стороне потока сервера");
            }
        }
    }
}
