package ru.geekbrains.java1.dz.dz7.GrafficSB;

import ru.geekbrains.java1.dz.dz7.АлександрВасиленко.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 29.05.2016.
 */
public class SeaButtle extends JFrame {

    private ButtleField bf1;
    private ButtleField bf2;

    private GameManagment gm1, gm2;
    private GridBagLayout gblayout;

    private Menu menu;
    private int menuItem;

    private JPanel p1;

    private JLabel name1;
    private JLabel name2;

    public SeaButtle() {

        super("SeaButtle");
        setLayout(new FlowLayout());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        p1 = new JPanel(gblayout);


        bf1 = new ButtleField();
        bf2 = new ButtleField();

        //размещение меню
        menu = new Menu();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gblayout.setConstraints(menu, gbc);
        p1.add(menu);

        gbc.gridwidth = 1;
        //размещение строки управления
        gm1 = new GameManagment();
        gm1.setDisable();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gblayout.setConstraints(gm1, gbc);
        p1.add(gm1);

        gm2 = new GameManagment();
        gm2.setDisable();

        gbc.gridx = 1;
        gbc.gridy = 1;
        gblayout.setConstraints(gm2, gbc);
        p1.add(gm2);


        //размещение имён игроков
        name1 = new JLabel("  ");
        name2 = new JLabel("  ");

        gbc.gridx = 0;
        gbc.gridy = 3;
        gblayout.setConstraints(name1, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gblayout.setConstraints(name2, gbc);
        p1.add(name1);
        p1.add(name2);

        //размещение полей с кораблями
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.ipadx = 9;
        gblayout.setConstraints(bf1, gbc);
        p1.add(bf1);
        bf1.setEnabled(false);
        bf1.setVisible(true);
        gbc.gridy = 4;
        gbc.gridx = 1;
        gblayout.setConstraints(bf2, gbc);
        p1.add(bf2);
        bf2.setEnabled(false);
        bf2.setVisible(true);

        //размещение основной панели
        add(p1);

        //Игра
        menu.getPvc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.getPvc().isSelected()) {
                    menuItem = 1;
                    menu.getPvp().setSelected(false);
                    menu.getCvc().setSelected(false);
                    menu.setDisable();
                    setGameSetting(menuItem);
                }
            }
        });
        menu.getPvp().addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.getPvp().isSelected()) {
                    menuItem = 2;
                    menu.getPvc().setSelected(false);
                    menu.getCvc().setSelected(false);
                    menu.setDisable();
                    setGameSetting(menuItem);
                }
            }
        });
        menu.getCvc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menu.getCvc().isSelected()) {
                    menuItem = 3;
                    menu.getPvp().setSelected(false);
                    menu.getPvc().setSelected(false);
                    menu.setDisable();
                    setGameSetting(menuItem);
                }
            }
        });



    }



    private void setGameSetting(int menuItem) {
        if (menuItem == 1) {
            gm1.setDisable();
            gm2.setEnable();
            setLabelName("Поле компьютера", "Поле игрока");
            //Game
            AIPlayer p1 = new AIPlayer();
            gm2.getStrikeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Player
                    int xS = Integer.parseInt(gm2.getXString()) - 1;
                    int yS = Integer.parseInt(gm2.getYString()) - 1;
                    if (xS >= 0 && xS < 10 && yS >= 0 && yS < 10) {
                        bf1.strike(xS, yS);
                        bf1.printField();
                        gm2.setNull();
                        bf1.updateUI();

                    }
                    //AI
                    p1.turn(bf2);
                    bf2.printField();
                    bf2.updateUI();
                }
            });
        } else if (menuItem == 2) {
            gm1.setEnable();
            gm2.setEnable();
            setLabelName("Поле игрока 1", "Поле игрока 2");
            //Game
            gm2.getStrikeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Player1
                    int xS1 = Integer.parseInt(gm2.getXString()) - 1;
                    int yS1 = Integer.parseInt(gm2.getYString()) - 1;
                    if (xS1 >= 0 && xS1 < 10 && yS1 >= 0 && yS1 < 10) {
                        bf1.strike(xS1, yS1);
                        bf1.printField();
                        gm2.setNull();
                        bf1.updateUI();
                    }
                }
            });
            gm1.getStrikeButton().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Player2
                    int xS2 = Integer.parseInt(gm1.getXString()) - 1;
                    int yS2 = Integer.parseInt(gm1.getYString()) - 1;
                    if (xS2 >= 0 && xS2 < 10 && yS2 >= 0 && yS2 < 10) {
                        bf2.strike(xS2, yS2);
                        bf2.printField();
                        gm1.setNull();
                        bf2.updateUI();
                    }
                }
            });
        } else if (menuItem == 3) {
            gm1.setDisable();
            gm2.setDisable();
            setLabelName("Поле компьютера 1", "Поле компьютера 2");
            AIPlayer p1 = new AIPlayer();
            AIPlayer p2 = new AIPlayer();
            while(true){
                //AI 1
                p1.turn(bf2);
                bf2.printField();
                bf2.updateUI();
                if(bf2.isDefeated())break;
                //AI 2
                p2.turn(bf1);
                bf1.printField();
                bf1.updateUI();
                if(bf1.isDefeated())break;
            }
        }
    }


    public void setLabelName(String name1, String name2) {
        this.name1.setText(name1);
        this.name2.setText(name2);
        this.name1.setVisible(true);
        this.name2.setVisible(true);
    }

    public GameManagment getGm2() {
        return gm2;
    }

    public ButtleField getBf1() {
        return bf1;
    }
}
