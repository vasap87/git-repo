package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 27.06.2016.
 * Поток сервера, для работы с клиентами,где будет происходить отправка сообщений от остальных пользователей
 *
 * @version 0.0
 * @see java.lang.Runnable
 */
public class ServerThread implements Runnable {

    //константа обозначающая оптраку пользователем команды выход
    private static final String END = "end";

    //переменные класса
    private Socket socket;
    private Server server;
    private String name = "";
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Конструктор класса
     *
     * @param socket сокет клиента
     * @param server экземляр сервера, чтобы можно было пользоваться методати класса
     */
    public ServerThread(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("");
        }
    }

    /**
     * Переопределённый метод интерфейса Runable, в котором происходит запуск потока
     */
    @Override
    public void run() {
        try {
            while (true) {
                String fromUser = in.readUTF();
                //если имя пустое, то мы авторизуемся
                if (name.isEmpty()) {
                    String[] strings = fromUser.split("\t");
                    String getsName = SQLTools.getInstance().getNickNameByLoginAndPassword(strings[0], strings[1]);
                    //если результат запроса есть
                    if (getsName != null) {
                        this.name = getsName;
                        sendMsg("authorisation");
                    }
                    //если нет такой комбинации
                    else {
                        sendMsg("Ошибка авторизации");
                        server.removeTread(this);
                        break;
                    }
                    fromUser = null;
                } else {
                    server.sendMSGToAllClients(name + ": " + fromUser);
                    if (fromUser.trim().equalsIgnoreCase(END)) break;
                }
                Thread.sleep(100);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Проблема с входным потоком на стороне потока сервера");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Проблема с остановкой потока");
        }
        //если дошли до сюда, значит клиент отключился
        try {
            if (name.equals("")) {
                server.removeTread(this);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод отправки сообщения в исходящий поток только пользователю
     */
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения, подробнее: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
