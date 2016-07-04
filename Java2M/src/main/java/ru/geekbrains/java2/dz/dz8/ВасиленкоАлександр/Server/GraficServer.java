package ru.geekbrains.java2.dz.dz8.ВасиленкоАлександр.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by vasilenko.aleksandr on 01.07.2016.
 * Grafic view of chat server.
 */
public class GraficServer extends JFrame {

    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;
    private JTextArea jTextArea;

    public GraficServer() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setTitle("Сервер чата");
        //Start sql connection


        jTextArea = new JTextArea();
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        add(jScrollPane);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //Sql disconnected
                SQLTools.getInstance().setDisconnection();
            }
        });
        setVisible(true);
    }

    public void addTextToTextArea(String s) {
         jTextArea.append(s+"\n");
    }
}
