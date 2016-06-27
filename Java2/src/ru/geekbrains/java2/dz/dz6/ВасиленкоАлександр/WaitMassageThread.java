package ru.geekbrains.java2.dz.dz6.ВасиленкоАлександр;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 24.06.2016.
 */
public class WaitMassageThread implements Runnable {
    private ChatJFrame chatJFrame;
    private String login;
    private Scanner in;

    public WaitMassageThread(ChatJFrame chatJFrame, String login, Scanner in) {
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
                    Date date = new Date();
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    StringBuilder sbInput = new StringBuilder("[" + currentDate.format(date) + "] <b>Server</b> to "
                            +"<b>["+login+"]</b> "+ s + "<br>");
                    chatJFrame.addMessageToJTextPane(sbInput.toString());
                    chatJFrame.writeInFile(sbInput.toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Проблема чтения строки из входного потока на клиенте.");
        }
    }
}
