package ru.geekbrains.java1.dz.dz7.GrafficSB;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 29.05.2016.
 */
public class SeaButtle extends JFrame {

    private ButtleField pp1;
    private ButtleField pp2;
    private Random rand = new Random();
    public final char WATER = '~'; //вода
    private final char SHIP = 'O';   //корабль
    public final char MISS = '*';   //промах
    private final char HIT = 'X';    //попадание
    public final static int SIZE = 10;    //размер поля
    private boolean isHit;          //Попал ли стрелок по кораблю

    public SeaButtle(){

        super("SeaButtle");
        setLayout(new FlowLayout());
        setSize(900,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagLayout gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel p1 = new JPanel(gblayout);
        JLabel name1  = new JLabel("Компьютер");
        JLabel name2 = new JLabel("Игрок");

        pp1 = new ButtleField();
        pp2 = new ButtleField();

        //размещение меню
        Menu menu = new Menu();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gblayout.setConstraints(menu,gbc);
        p1.add(menu);

        //размещение строки управления
        GameManagment gm = new GameManagment(pp1);
        gbc.gridx=0;
        gbc.gridy=1;
        gblayout.setConstraints(gm,gbc);
        p1.add(gm);

        gbc.gridwidth=1;

        //размещение имён игроков
        gbc.gridx=0;
        gbc.gridy=2;
        gblayout.setConstraints(name1,gbc);
        gbc.gridx=1;
        gbc.gridy=2;
        gblayout.setConstraints(name2,gbc);
        p1.add(name1);
        p1.add(name2);

        //размещение полей с кораблями
        gbc.gridy=3;
        gbc.gridx=0;
        gbc.ipadx=9;
        gblayout.setConstraints(pp1,gbc);
        p1.add(pp1);
        pp1.setVisible(true);
        gbc.gridy=3;
        gbc.gridx=1;
        gblayout.setConstraints(pp2,gbc);
        p1.add(pp2);
        pp2.setVisible(true);

        //размещение основной панели
        add(p1);

    }
}
