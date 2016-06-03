package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 */
public class XOGame extends JFrame {
    public XOGame(){
        setTitle("XO");
        setSize(200,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(JField.getInstance());
        setVisible(true);
    }
}
