package ex6BinaryNetworkChat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 10.08.2016.
 */
public class Client {
    private final String HOST = "localhost";
    private final int PORT = 17000;

    public void start() {
        try (Socket socket = new Socket(HOST, PORT);
             DataInputStream dis = new DataInputStream(socket.getInputStream())) {
            //приём файла с сервера
            String fileName = dis.readUTF();
            FileOutputStream fos = new FileOutputStream(new File("D:\\Study\\GB\\git-repo\\Java3\\Lesson5\\2FromStream\\"+fileName));
            int i;
            while((i=dis.read())!=-1){
                fos.write(i);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }

}
