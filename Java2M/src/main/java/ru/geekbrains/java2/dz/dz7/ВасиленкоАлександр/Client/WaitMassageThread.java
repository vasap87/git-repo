package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Client;

import java.io.DataInputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 * Класс принимающий сообщение от сервера и отправляющего их в поле чата.
 * Реализует интерфейс Runnable, для работы в паралельном потоке
 */
public class WaitMassageThread implements Runnable {
    private ChatJFrame chatJFrame;
    private Socket socket;

    /**
     *  Конструктор потока*/
    public WaitMassageThread(ChatJFrame chatJFrame, Socket socket) {
        this.chatJFrame = chatJFrame;
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //работаем только с входным потоком
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true) {
                //если сокет существует
                if(!socket.isClosed()) {
                    String fromServer = in.readUTF();
                    String[] massage = fromServer.split("\t");
                    //проверям ключ строки
                    switch (massage[0]){
                        //если пришло сообщение
                        case "msg":{
                            chatJFrame.addMessageToJTextPane(massage[1]);
                            chatJFrame.writeInFile(massage[1]);
                            break;
                        }
                        //если пришло обновление для списка пользователей
                        case "users":{
                            StringBuilder users = new StringBuilder();
                            for (int i = 1; i < massage.length; i++) {
                                users.append("\t"+massage[i]);
                            }
                            chatJFrame.addUserToList(users.toString());
                            break;
                        }
                    }
                    fromServer = "";
                }else break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Проблема чтения строки из входного потока на клиенте.");
        }
    }
}
