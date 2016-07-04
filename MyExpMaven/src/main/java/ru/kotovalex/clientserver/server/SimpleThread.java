package ru.kotovalex.clientserver.server;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 04.07.2016.
 */
public class SimpleThread implements Runnable {
    private Socket socket;
//    private DataInputStream in;
    private DataOutputStream out;
    private String apiKey = "co703595869617843294575061623572";

    public SimpleThread(Socket socket) {
        try {
            this.socket = socket;
//            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            out.writeUTF("Connected\t");
            out.flush();
            //получение XML
            URL url = new URL("http://partners.api.skyscanner.net/apiservices/hotels/autosuggest/v2/UK/EUR/en-GB/pari?apikey=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            Scanner sc = new Scanner(connection.getInputStream());

            out.writeUTF("json\t"+sc.nextLine());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.writeUTF("DisConnected\t");
                out.flush();
//           in.close();
//                out.close();
//                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
