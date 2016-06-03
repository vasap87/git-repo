package ru.vasilenkoaleksandr.myProjects.GrafficSB_v2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 01.06.2016.
 */
public class SeaButtle extends JFrame {

    private SimpleButtleField bf1,bf2;
    private GridBagConstraints gbc;
    private JFrame frame;
    private JLabel name1,name2;
    private int gameType=1;

    public SeaButtle() {
        frame = new JFrame();
        frame.setTitle("SeaBattle v.2(vasap87)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 400);
        frame.setResizable(false);

        addJMenu();

        GridBagLayout gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        frame.setLayout(gbl);

        name1 = new JLabel();
        name2 = new JLabel();
        bf1= new PlayerBF(true, null);
        bf2= new PlayerBF(true, null);
        setGameSetting(gameType);

        frame.setVisible(true);

    }
    //Настройка игры
    private void setGameSetting(int i) {
        switch (i){
            case 1:{
                frame.remove(name1);
                frame.remove(name2);
                frame.remove(bf1);
                frame.remove(bf2);
                name1.setText("Comp field");
                name2.setText("User field");
                gbc.gridy=0;
                gbc.gridx=0;
                frame.add(name1,gbc);
                gbc.gridx=1;
                frame.add(name2,gbc);
                gbc.gridx=0;
                gbc.gridy=1;
                CompBF bf2= new CompBF(false);
                bf1= new PlayerBF(true, bf2);
                frame.add(bf1,gbc);
                gbc.gridx=1;

                frame.add(bf2,gbc);
                bf1.updateUI();
                bf2.updateUI();
                gameType=1;
                break;
            }
            case 2:{
                frame.remove(name1);
                frame.remove(name2);
                frame.remove(bf1);
                frame.remove(bf2);
                name1.setText("User field 1");
                name2.setText("User field 2");
                gbc.gridy=1;
                gbc.gridx=0;
                frame.add(name1,gbc);
                gbc.gridx=1;
                frame.add(name2,gbc);
                gbc.gridx=0;
                gbc.gridy=2;
                bf1= new PlayerBF(false, null);
                frame.add(bf1,gbc);
                gbc.gridx=1;
                bf2= new PlayerBF(false, null);
                frame.add(bf2,gbc);
                bf1.updateUI();
                bf2.updateUI();
                gameType=2;
                break;
            }

            default:break;
        }
    }

    private void addJMenu() {
        //Добавление меню
        JMenuBar menuBar = new JMenuBar();
        Font menuFont = new Font("Veranda", Font.PLAIN, 12);
        //меню игра
        JMenu menu = new JMenu("Game");
        menu.setFont(menuFont);
        //меню режим игры
        JMenu menuType = new JMenu("Type");
        menuType.setFont(menuFont);
        menu.add(menuType);
        //режимы игры
        JMenuItem pvc = new JMenuItem("player vs comp");
        JMenuItem pvp = new JMenuItem("player vs player");
        pvc.setFont(menuFont);
        pvc.addActionListener(e -> setGameSetting(1));
        pvp.setFont(menuFont);
        pvp.addActionListener(e -> setGameSetting(2));
        menuType.add(pvc);
        menuType.add(pvp);
        //заного
        JMenuItem reTry = new JMenuItem("Replay");
        reTry.setFont(menuFont);
        reTry.addActionListener(e -> setGameSetting(gameType));
        menu.add(reTry);
        //выход
        JMenuItem exit = new JMenuItem("Exit");
        exit.setFont(menuFont);
        menu.add(exit);
        exit.addActionListener(e -> System.exit(0));
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        //Конец меню
    }

}
