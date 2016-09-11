package serialization;

import java.io.Serializable;

/**
 * Created by vasilenko.aleksandr on 08.09.2016.
 */
public class Message implements Serializable {
    String mess;

    public Message(String mess) {
        this.mess = mess;
    }

    @Override
    public String toString() {
        return "Message{" +
                "\'" + mess + '\'' +
                '}';
    }
}
