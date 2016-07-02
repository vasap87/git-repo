package ru.geekbrains.java2.dz.dz8.ВасиленкоАлександр.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vasilenko.aleksandr on 27.06.2016.
 * Поток сервера, для работы с клиентами,где будет происходить отправка сообщений от остальных пользователей
 *
 * @version 0.0
 * @see Runnable
 */
public class ServerThread implements Runnable {

    //константа обозначающая оптраку пользователем команды выход
    private static final String EXIT = "exit";

    //переменные класса
    private Socket socket;
    private Server server;
    private GraficServer grafficServer;
    private String name = "";
    private DataInputStream in;
    private DataOutputStream out;

    /**
     * Конструктор класса
     *
     * @param socket        сокет клиента
     * @param server        экземляр сервера, чтобы можно было пользоваться методати класса
     * @param grafficServer для логирования операция на сервере
     */
    public ServerThread(Socket socket, Server server, GraficServer grafficServer) {

        this.grafficServer = grafficServer;
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
            loggingOper("попытка соединения");
            while (true) {
                //читаем что пришло от пользователя
                String fromUser = in.readUTF();
                String[] messages = fromUser.split("\t");
                switch (messages[0]) {
                    //если запрос на регистрацию
                    case "registration": {
                        //Логируем операцию
                        loggingOper("Пришёл запрос на регистацию");
                        //проверяем есть ли в базе с таким логином и паролем
                        String getNickname = SQLTools.getInstance().getNickNameByLoginAndPassword(messages[2], messages[3]);
                        //если нет, то регистрируем
                        if (getNickname == null) {
                            SQLTools.getInstance().registerNewUser(messages[1], messages[2], messages[3]);
                            //Логируем операцию
                            loggingOper("Успешная регистрация никнейма " + messages[1]);
                            sendMsg("good_reg", false, null);
                        }
                        //если есть
                        else {
                            //Логируем операцию
                            loggingOper("не удалось зарегистрировать никнейм " + messages[1]);
                            sendMsg("bad_reg", false, null);
                        }
                        break;
                    }
                    //если запрос на авторизацию
                    case "authorisation": {
                        //Логируем операцию
                        loggingOper("Пришёл запрос на авторизацию");
                        String getNickname = SQLTools.getInstance().getNickNameByLoginAndPassword(messages[1], messages[2]);
                        //если результат запроса есть
                        if (getNickname != null) {
                            this.name = getNickname;
                            //Логируем операцию
                            loggingOper("Успешная авторизация никнейма " + name);
                            sendMsg("good", false, null);
                            server.sendUsersToALLClients();
                            server.sendMSGToAllClients(buildMessage("<u>Присоеденился к чату</u>", false,null), false, null);
                        }
                        //если нет такой комбинации
                        else {
                            //Логируем операцию
                            loggingOper("Не успешная авторизация никнейма " + name);
                            sendMsg("no good", false, null);
                        }
                        break;
                    }
                    //если пришло сообщение
                    case "send": {
                        //если кользователь прислал запрос на выход
                        if (messages[1].trim().equalsIgnoreCase(EXIT)) break;
                        if(messages[2].equals(" ")){
                            server.sendMSGToAllClients(buildMessage(messages[1], false, null), false, null);
                        }else {
                            server.sendMSGToAllClients(buildMessage(messages[1], true, messages[2]), true, messages[2]);
                        }
                        break;
                    }
                    //если пользоваетль отключисля
                    case "quit": {
                        //Логируем операцию
                        loggingOper("Пользователь " + name + " отключился от чата");
                        server.sendMSGToAllClients(buildMessage("<u>Отключился от чата</u>", false, null), false, null);
                        server.removeTread(this);
                        server.sendUsersToALLClients();
                        socket.close();
                        break;
                    }
                }
                if (fromUser.trim().contains(EXIT)) break;
                Thread.sleep(100);
            }
        } catch (IOException e) {
            System.out.println("Проблема с входным потоком на стороне потока сервера, причина: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Проблема с остановкой потока, причина: " + e.getMessage());
        }
    }

    /**
     * Метод сборки сообщения
     */
    public String buildMessage(String message, boolean privateMSG, String toLogin) {
        Date date = new Date();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        StringBuilder sbInput = new StringBuilder("[" + currentDate.format(date) + "] <b>" + name + ":</b> " +
                (privateMSG?"to "+ "<b>" + toLogin + ":</b> " : "") + message + "<br>");
        return sbInput.toString();
    }

    /**
     * метод отправки сообщения в исходящий поток к пользователю
     */
    public void sendMsg(String msg, boolean hasKey, String key) {
        try {
            if (hasKey) {
                out.writeUTF(key + "\t" + msg);
            } else {
                out.writeUTF(msg);
            }
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения, подробнее: " + e.getMessage());
        }
    }

    /**
     * метод рассылки пользователей чата
     */
    public void sendUserList(String users) {
        try {
            out.writeUTF("users" + users);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    private void loggingOper(String s) {
        grafficServer.addTextToTextArea(s + " от: " + socket.toString());
    }
}
