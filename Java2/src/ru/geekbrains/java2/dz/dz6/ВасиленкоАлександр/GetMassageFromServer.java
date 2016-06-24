package ru.geekbrains.java2.dz.dz6.ВасиленкоАлександр;

import javax.swing.*;
import java.io.BufferedReader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 */
public class GetMassageFromServer implements Runnable {
    private ChatJFrame chatJFrame;
    private String login;
    private Scanner in;

    public GetMassageFromServer(ChatJFrame chatJFrame, String login, Scanner in) {
        this.chatJFrame = chatJFrame;
        this.login = login;
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (in.hasNextLine()) {
                    String s = in.nextLine();
                    GregorianCalendar calendar = new GregorianCalendar();
                    StringBuilder currentDate = new StringBuilder();
                    currentDate
                            //день месяца
                            .append((calendar.get(Calendar.DAY_OF_MONTH) < 10 ? 0 + "" + calendar.get(Calendar.DAY_OF_MONTH) : calendar.get(Calendar.DAY_OF_MONTH)) + ".")
                            //месяц
                            .append((calendar.get(Calendar.MONTH) < 9 ? 0 + "" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1)) + ".")
                            //год
                            .append(calendar.get(Calendar.YEAR) + " ")
                            //час
                            .append((calendar.get(Calendar.HOUR_OF_DAY) < 10 ? 0 + "" + calendar.get(Calendar.HOUR_OF_DAY) : calendar.get(Calendar.HOUR_OF_DAY)) + ":")
                            //минуты
                            .append((calendar.get(Calendar.MINUTE) < 10 ? 0 + "" + calendar.get(Calendar.MINUTE) : calendar.get(Calendar.MINUTE)) + ":")
                            //секунды
                            .append((calendar.get(Calendar.SECOND) < 10 ? 0 + "" + calendar.get(Calendar.SECOND) : calendar.get(Calendar.SECOND)));

                    StringBuilder sbInput = new StringBuilder("[" + currentDate.toString() + "] <b>Server</b> to "
                            +"<b>["+login+"]</b> "+ s + "<br>");
                    chatJFrame.addMessageToJTextPane(sbInput.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Проблема чтения строки из входного потока на клиенте.");
        }
    }
}
