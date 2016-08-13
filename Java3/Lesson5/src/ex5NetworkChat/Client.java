package ex5NetworkChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    protected static Logger log = LoggerFactory.getLogger(Client.class);

    public static final int PORT = 19000;
    public static final String HOST = "localhost";

    private static final String EXIT = "exit";

    public static void main(String[] args) {

        Socket socket = null;
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            log.error("Соединение не возможно установить");
        }

        if (socket != null) {

            // потоки данных net
            BufferedReader in = null;
            PrintWriter out = null;
            BufferedReader console = null;
            try {
                in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));


                out = new PrintWriter(
                        socket.getOutputStream(), true);

                // консоль
                console = new BufferedReader(
                        new InputStreamReader(System.in));

                String line;
                String result;

                System.out.println("Please type...");

                while ((line = console.readLine()) != null) {
                    if (EXIT.equalsIgnoreCase(line)) {
                        log.info("Closing chat");
                        break;
                    }


                    out.println(line);
                    out.flush();

                    result = in.readLine();
                    System.out.println(">> " + result);

                }

            } catch (SocketException e){
                log.error("Соединенение прервано");
            }catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(console!=null){
                    try {
                        console.close();
                    } catch (IOException e) {
                        log.error("Ошибка закрытия BufferReader консоли");
                    }
                }
                if(out!=null){
                    out.close();
                }
                if(in!=null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        log.error("Ошибка закрытия BufferReader потока с сервера");
                    }
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                log.error("Ошибка закрытия Socket на клиенте");
            }
        }
    }
}
