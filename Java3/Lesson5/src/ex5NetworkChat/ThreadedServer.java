package ex5NetworkChat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedServer {

    protected static Logger log = LoggerFactory.getLogger("ThreadedServer");
    private static final int PORT = 19000;
    private static int counter = 0;

    // список обработчиков для клиентов
    private List<ClientHandler> handlers = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ThreadedServer server = new ThreadedServer();
        server.startServer();
    }

    public void startServer() throws Exception {
        log.info("Starting server...");
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true) {

            // блокируемся и ждем клиента
            Socket socket = serverSocket.accept();
            log.info("Client connected: " + socket.getInetAddress().toString() + ":" + socket.getPort());

            // создаем обработчик
            ClientHandler handler = new ClientHandler(this, socket, counter++);
            handlers.add(handler);
            handler.start();
        }
    }

    /*
    Для каждого присоединившегося пользователя создается поток обработки независимый от остальных
     */

    class ClientHandler extends Thread {

        private ThreadedServer server;
        private BufferedReader in;
        private PrintWriter out;
        private String login;
        private String toLogin;
        private final String HELP = "\n.login - залогиниться в чате\n.help - вывод списка команд" +
                "\n.private <user_name>\n.exit  - выход из чата";
        private boolean isLogin;
        private boolean isExit;

        // номер, чтобы различать потоки
        private int number;

        public String getLogin() {
            return login;
        }

        public ClientHandler(ThreadedServer server, Socket socket, int counter) throws Exception {
            this.server = server;
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            number = counter;
        }


        // Отправка сообщения в сокет, связанный с клиентом
        public void send(String message) {
            out.println(message);
            out.flush();
        }

        @Override
        public void run() {

            // В отдельном потоке ждем данных от клиента
            try {
                String line = null;
                while ((line = in.readLine()) != null) {
                    log.info("Handler[" + number + "]<< " + line);
                    String[] parts = line.split("\t");
                    switch (parts[0].toLowerCase()){
                        case ".login" :{
                            login = parts[2];
                            isLogin = true;
                            break;
                        }
                        case ".exit" :{
                            handlers.remove(this);
                            if(isLogin)server.broadcast(login + "покинул чат", false, null, null);
                            isExit = true;
                            break;
                        }
                        case ".private" :{
                            toLogin = parts[1];
                            server.broadcast(login + " to ["+toLogin+"]"+ parts[2], true, toLogin, login);
                            break;
                        }
                        case ".help":{
                            out.write(HELP);
                            out.flush();
                        }
                        default:{
                            if(isLogin) server.broadcast(login+ " said: "+parts[2], false, null, null);
                            break;
                        }
                    }
                    //передать управление в финальный блок
                    if (isExit) break;
                }

            } catch (
                    IOException e)

            {
                log.error("Failed to read from socket");
            } finally

            {
                Util.closeResource(in);
                Util.closeResource(out);
            }
        }



    }

    // рассылаем всем подписчикам
    public void broadcast(String msg, boolean isPrivate, String toLogin, String fromLogin) {
        log.info("Broadcast to all: " + msg);
        for (ClientHandler handler : handlers) {
            if(isPrivate){
                if(handler.getLogin().equalsIgnoreCase(toLogin)||handler.getLogin().equalsIgnoreCase(fromLogin)){
                    handler.send(msg);
                }
            }else{
                handler.send(msg);
            }

        }
    }


}
