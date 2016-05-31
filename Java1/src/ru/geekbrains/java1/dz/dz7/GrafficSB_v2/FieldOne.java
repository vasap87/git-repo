package ru.geekbrains.java1.dz.dz7.GrafficSB_v2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 31.05.2016.
 */
public class FieldOne extends JButton {
    int xArea;
    int yArea;
    public FieldOne(int x,int y,String label){
        super(label);
        this.xArea = x;
        this.yArea = y;
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //вернуть значения xArea и yArea для определения места выстрела
            }
        });
    }
}
