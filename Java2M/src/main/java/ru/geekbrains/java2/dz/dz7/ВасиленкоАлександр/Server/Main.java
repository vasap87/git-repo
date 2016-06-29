package ru.geekbrains.java2.dz.dz7.ВасиленкоАлександр.Server;

/**
 * Created by vasilenko.aleksandr on 28.06.2016.
 */
public class Main {
    public static void main(String[] args) {
        SQLTools.getInstance().setConnection();
        Server server = new Server();
    }
}
