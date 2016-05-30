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

    private GameManagment gm1, gm2;

    private GridBagLayout gblayout;

    private Menu menu;

    private  JPanel p1;

    private JLabel name1;
    private JLabel name2;

    public SeaButtle(){

        super("SeaButtle");
        setLayout(new FlowLayout());
        setSize(900,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gblayout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        p1 = new JPanel(gblayout);


        pp1 = new ButtleField();
        pp2 = new ButtleField();

        //размещение меню
        menu = new Menu();
        menu.setEnable();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridwidth=2;
        gblayout.setConstraints(menu,gbc);
        p1.add(menu);

        gbc.gridwidth=1;
        //размещение строки управления
        gm1 = new GameManagment();
        gm1.setEnable();
        gm1.strikeButton(getPp2());
        gbc.gridx=0;
        gbc.gridy=1;
        gblayout.setConstraints(gm1,gbc);
        p1.add(gm1);

        gm2 = new GameManagment();
        gm2.setEnable();
        gm2.strikeButton(getPp1());

        gbc.gridx=1;
        gbc.gridy=1;
        gblayout.setConstraints(gm2,gbc);
        p1.add(gm2);



        //размещение имён игроков
        name1=new JLabel("  ");
        name2=new JLabel("  ");

        gbc.gridx=0;
        gbc.gridy=3;
        gblayout.setConstraints(name1,gbc);
        gbc.gridx=1;
        gbc.gridy=3;
        gblayout.setConstraints(name2,gbc);
        p1.add(name1);
        p1.add(name2);

        //размещение полей с кораблями
        gbc.gridy=4;
        gbc.gridx=0;
        gbc.ipadx=9;
        gblayout.setConstraints(pp1,gbc);
        p1.add(pp1);
        pp1.setVisible(true);
        gbc.gridy=4;
        gbc.gridx=1;
        gblayout.setConstraints(pp2,gbc);
        p1.add(pp2);
        pp2.setVisible(true);

        //размещение основной панели
        add(p1);




    }


    public void setLabelName(String name1, String name2){
        this.name1.setText(name1);
        this.name2.setText(name2);
        this.name1.setVisible(true);
        this.name2.setVisible(true);
    }

    public ButtleField getPp1() {
        return pp1;
    }

    public ButtleField getPp2() {
        return pp2;
    }

    public Menu getMenu() {
        return menu;
    }

    public GameManagment getGm1() {
        return gm1;
    }

    public GameManagment getGm2() {
        return gm2;
    }
}
