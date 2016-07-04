package ru.kotovalex.clientserver.client;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.parser.JSONParser;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 04.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8899);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Class.forName("com.google.gson.Gson");
            while (true) {
                String s = in.readUTF();
                String[] messages = s.split("\t");
                if (messages[0].equals("json")) {
                    //парсинг json
                } else {
                    System.out.println(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
