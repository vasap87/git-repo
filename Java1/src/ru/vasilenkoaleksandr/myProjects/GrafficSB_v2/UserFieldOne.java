package ru.vasilenkoaleksandr.myProjects.GrafficSB_v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Created by admin on 31.05.2016.
 */
public class UserFieldOne extends JButton {
    private int xArea;
    private int yArea;
    private Random rand = new Random();

    public UserFieldOne(int x, int y, String label, char[][] empty, char[][] ship, char mis, char hit, boolean gameType, CompBF compBF) {
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
                //если игра с компом, то ход компа
                if(gameType){
                    int x,y;
                    do {
                        x = rand.nextInt(empty.length);
                        y = rand.nextInt(empty.length);
                    }while(!compBF.compStrike(x,y));
                    //обновить поле куда стрелял комп
                    compBF.updateUI();
                }
            }
        });
    }
}
