package serialization;

import java.io.*;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 08.09.2016.
 */
public class ServerThread extends Thread {
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        try {
            Message message = (Message)ois.readObject();
            System.out.println("Server take mess: "+ message.toString());
            Message messFromServer = new Message("Привет клиенту!");
            oos.writeObject(messFromServer);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
