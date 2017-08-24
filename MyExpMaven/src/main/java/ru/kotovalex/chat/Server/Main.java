package ru.kotovalex.chat.Server;

/**
 * Created by vasilenko.aleksandr on 28.06.2016.
 */
public class Main {
    public static void main(String[] args) {
        GraficServer gs = new GraficServer();
        Server server = new Server(gs);
    }
}
