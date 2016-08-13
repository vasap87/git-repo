package ex5NetworkChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ThreadedClient {

    protected static Logger log = LoggerFactory.getLogger(ThreadedClient.class);

    public static final int PORT = 19000;
    public static final String HOST = "localhost";
    private static final String EXIT = ".exit";

    public static void main(String[] args) throws Exception {

        ThreadedClient client = new ThreadedClient();
        client.startClient();

    }

    public void startClient() {
        Socket socket = null;
        BufferedReader in = null;
        try {
            socket = new Socket(HOST, PORT);
            ConsoleThread console = new ConsoleThread(socket);
            console.start();

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(">> " + line);
                if (line.equalsIgnoreCase(EXIT)) break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.closeResource(in);
            Util.closeResource(socket);
        }
    }

    public void send(String message) {

    }

    class ConsoleThread extends Thread {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out;

        public ConsoleThread(Socket socket) throws Exception {
            out = new PrintWriter(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = console.readLine()) != null) {
                    if (EXIT.equalsIgnoreCase(line)) {
                        log.info("Closing chat");
                        break;
                    }
                    out.println(parseMsg(line));
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Util.closeResource(out);
            }
        }

        private String parseMsg(String line) {
            String msg = line;
            String key = null;
            if (line.startsWith(".")) {
                int spaceIndex = line.indexOf(' ');
                if (spaceIndex != -1) {
                    key = line.substring(0, spaceIndex);
                    msg = line.substring(spaceIndex + 1);
                } else {
                    key = line;
                }
            }
            String toLogin = null;
            if (key != null && key.equalsIgnoreCase(".private")) {
                int openTag = line.indexOf("<") + 1;
                int closeTag = line.indexOf(">");
                toLogin = line.substring(openTag, closeTag);
                msg = line.substring(closeTag + 1);
            }
            return key + "\t" + toLogin + "\t" + msg;
        }

    }

}
