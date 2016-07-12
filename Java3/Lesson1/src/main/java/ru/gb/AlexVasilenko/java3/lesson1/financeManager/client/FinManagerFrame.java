package ru.gb.AlexVasilenko.java3.lesson1.financeManager.client;

import javax.swing.*;
import java.net.Socket;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class FinManagerFrame extends JFrame {
    private Socket socket;
    private String login;

    public FinManagerFrame(Socket socket, String login){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.socket = socket;
        this.login = login;
    }
}
