package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 08.09.2016.
 */
public class Client {
    public static void main(String[] args) {
        Message mess = new Message("Привет серверу!");
        try (Socket socket = new Socket("localhost", 8899);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            oos.writeObject(mess);
            oos.flush();
            Message fromServ = (Message) ois.readObject();
            System.out.println("Client teke mess from server: " + fromServ.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
