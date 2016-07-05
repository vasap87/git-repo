package ru.geekbrains.java2.dz.dz8.ВасиленкоАлександр.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 * Основной класс для работы сервера. Создаётся серверный сокет,
 * далее происходит ожидание подключения пользователей. Как только
 * пользователь подключился, создаём объет пользователя реализующий
 * интерфейс Runnable, заносим его в коллекцию пользователей, для
 * дальнейшей отправки каждого сообщения всем пользователям чата.
 */
public class Server {
    private static final int PORT = 8188;
    //коллекция пользователей
    private List<ServerThread> clients = new ArrayList<ServerThread>();
    private GraficServer grafficServer;

    //единственный констркутор класса
    public Server(GraficServer grafficServer) {
        this.grafficServer = grafficServer;
        SQLTools.getInstance().setConnection();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            this.grafficServer.addTextToTextArea("Сервер запущен, ждём подключения пользователей");
            while (true) {
                //ждём подключения пользователя
                Socket socket = serverSocket.accept();
                /*после подключения создаём объет для получения сообщения
                от пользователя и трансляции его всем пользователям чата*/
                ServerThread serverThread = new ServerThread(socket, this, grafficServer);
                clients.add(serverThread);
                Thread thread = new Thread(serverThread);
                //запускаем поток
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * метод удаления из коллекции пользоватей
     */
    public void removeTread(ServerThread serverThread) {
        clients.remove(serverThread);
    }

    /**
     * метод трансляции сообщения всем пользователям
     */
    public void sendMSGToAllClients(String msg, boolean isPrivate, String toNickname, String fromNickname) {
        if (!isPrivate) {
            for (ServerThread serverThread : clients) {
                if(!serverThread.isPrivate()) {
                    serverThread.sendMsg(msg, true, "msg");
                }else if (serverThread.isPrivate() && serverThread.getName().equals(fromNickname)){
                    serverThread.sendMsg(msg, true, "msg");
                }
            }
        } else {
            for (ServerThread serverThread : clients) {
                if (serverThread.getName().equals(toNickname) || serverThread.getName().equals(fromNickname))
                    serverThread.sendMsg(msg, true, "msg");
            }
        }
    }

    /**
     * Метод трансляции списка пользователй всем пользователям
     */
    public void sendUsersToALLClients() {
        StringBuilder users = new StringBuilder();
        for (ServerThread serverThread : clients) {
            users.append("\t" + serverThread.getName());
        }
        for (ServerThread serverThread : clients) {
            serverThread.sendUserList(users.toString());
        }
    }

    public boolean isNicknameBusy(String getNickname) {
        for (ServerThread serverThread : clients) {
            if(serverThread.getName().equals(getNickname)) return true;
        }
        return false;
    }

    /**
     * Метод логирования операция сервера*/
}
