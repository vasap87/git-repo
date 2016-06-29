package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server;

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
 * @see java.lang.Runnable
 */
public class ServerThread implements Runnable {

    //константа обозначающая оптраку пользователем команды выход
    private static final String EXIT = "exit";

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
                //читаем что пришло от пользователя
                //пока сокет активен
                if (!socket.isClosed()) {
                    String fromUser = in.readUTF();
                    String[] messages = fromUser.split("\t");
                    switch (messages[0]) {
                        //если запрос на регистрацию
                        case "registration": {
                            //проверяем есть ли в базе с таким логином и паролем
                            String getNickname = SQLTools.getInstance().getNickNameByLoginAndPassword(messages[2],messages[3]);
                            //если нет, то регистрируем
                            if(getNickname == null){
                                SQLTools.getInstance().registerNewUser(messages[1],messages[2],messages[3]);
                                out.writeUTF("good_reg");
                                out.flush();
                            }
                            //если есть
                            else {
                                out.writeUTF("bad_reg");
                                out.flush();
                            }
                            break;
                        }
                        //если запрос на авторизацию
                        case "authorisation": {
                            String getNickname = SQLTools.getInstance().getNickNameByLoginAndPassword(messages[1], messages[2]);
                            //если результат запроса есть
                            if (getNickname != null) {
                                this.name = getNickname;
                                out.writeUTF("good");
                                out.flush();
                                server.sendUsersToALLClients();
                            }
                            //если нет такой комбинации
                            else {
                                out.writeUTF("no good");
                                out.flush();
                            }
                            break;
                        }
                        //если пришло сообщение
                        case "send": {
                            Date date = new Date();
                            SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                            StringBuilder sbInput = new StringBuilder("[" + currentDate.format(date) + "] <b>" + name + "</b> : "
                                    + messages[1] + "<br>");
                            //если кользователь прислал запрос на выход
                            if (messages[1].trim().equalsIgnoreCase(EXIT)) break;
                            server.sendMSGToAllClients(sbInput.toString());
                            break;
                        }
                        //если пользоваетль отключисля
                        case "exit": {
                            server.removeTread(this);
                            server.sendUsersToALLClients();
                            socket.close();
                            break;
                        }
                    }
                    if (fromUser.trim().contains(EXIT)) break;
                    Thread.sleep(100);
                } else {
                    in.close();
                    out.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Проблема с входным потоком на стороне потока сервера, причина: "+e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println("Проблема с остановкой потока, причина: "+e.getMessage());
        }
        //если дошли до сюда, значит клиент отключился
        try {
            if (!socket.isClosed()) {
                server.removeTread(this);
                server.sendUsersToALLClients();
                socket.close();
            } else {
                server.removeTread(this);
                server.sendUsersToALLClients();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод отправки сообщения в исходящий поток к пользователю
     */
    public void sendMsg(String msg) {
        try {
            out.writeUTF("msg\t"+msg);
            out.flush();
        } catch (IOException e) {
            System.out.println("Ошибка при отправке сообщения, подробнее: " + e.getMessage());
            e.printStackTrace();
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
}
