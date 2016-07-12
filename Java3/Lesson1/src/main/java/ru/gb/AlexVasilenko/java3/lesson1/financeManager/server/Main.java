package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server;

import ru.gb.AlexVasilenko.java3.lesson1.financeManager.server.Tools.SQLTools;

/**
 * Created by admin on 10.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        SQLTools.getInstance().setConnection();
        Server server = new Server();
    }
}
