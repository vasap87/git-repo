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
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                int x = e.getX();
//                int y = e.getY();
//                Object obj = getGraphics();
//                System.out.println(obj);
//                System.out.println("x:"+x+"\n"+"y:"+y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("x:"+x+"\n"+"y:"+y);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("x:"+x+"\n"+"y:"+y);
            }
        });
        setVisible(true);
    }
}
