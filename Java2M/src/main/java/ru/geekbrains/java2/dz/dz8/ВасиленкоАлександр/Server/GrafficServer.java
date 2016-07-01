package ru.geekbrains.java2.dz.dz8.ВасиленкоАлександр.Server;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 01.07.2016.
 */
public class GrafficServer extends JFrame {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 400;

    public GrafficServer(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,WIDTH,HEIGHT);
        setMinimumSize(new Dimension(WIDTH,HEIGHT));
        JTextArea jTextArea = new JTextArea();

    }
}
