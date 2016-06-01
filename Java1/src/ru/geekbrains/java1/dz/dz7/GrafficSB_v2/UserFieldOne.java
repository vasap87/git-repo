package ru.geekbrains.java1.dz.dz7.GrafficSB_v2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 31.05.2016.
 */
public class UserFieldOne extends JButton {
    private int xArea;
    private int yArea;

    public UserFieldOne(int x, int y, String label, char[][] empty, char[][] ship, char mis, char hit, boolean gameType) {
        super(label);
        setPreferredSize(new Dimension(30, 30));
        setMargin(new Insets(5, 5, 5, 5));
        this.xArea = x;
        this.yArea = y;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                Есть ли тут корабл?
                если есть поставить Х и сделать кнопку неактивной
                */
                if (empty[xArea][yArea] == ship[xArea][yArea]) {
                    setText(mis + "");
                    setEnabled(false);
                } else {
                    setText(hit + "");
                    setEnabled(false);
                }

                if(gameType){
                    System.out.println(122);
                }
            }
        });
    }
}
