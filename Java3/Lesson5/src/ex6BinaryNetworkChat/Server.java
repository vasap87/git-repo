package ex6BinaryNetworkChat;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vasilenko.aleksandr on 09.08.2016.
 */
public class Server {
    //Logger log = LoggerFactory.getLogger(Server.class);
    Set<ServerThread> clients = new HashSet<>();

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            ServerSocket ss = new ServerSocket(17000);
            while (true) {
                Socket clientSocket = ss.accept();
                System.out.println("Подключился клиент!");
                ServerThread serverThread = new ServerThread(clientSocket);
                clients.add(serverThread);
                Thread thread = new Thread(serverThread);
                thread.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
            //log.error("Не удалось создать серверный сокет, подробности:" + e.getMessage());
        }
    }


    class ServerThread implements Runnable {
        Socket clientSocket;

        public ServerThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            String filePath = "D:\\Study\\GB\\git-repo\\Java3\\Lesson5\\1ToStream\\test.txt";
            try (DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream())) {
                File sendFile = new File(filePath);
                FileInputStream fis = new FileInputStream(sendFile);
                dos.writeUTF(sendFile.getName());
                dos.flush();
                System.out.println("Отправили имя файла");
                int i;
                while((i = fis.read())!=-1){
                    dos.write(i);
                    dos.flush();
                }
                System.out.println("Отправили содержимое файла");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
