package ru.geekbrains.java1.dz.dz7.GrafficSB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;

/**
 * Created by admin on 29.05.2016.
 */
public class GameManagment extends JPanel {
    private JTextField x;
    private JTextField y;
    private JButton strikeButton;

    public GameManagment() {
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel xLabel = new JLabel("Ряд:  ", JLabel.RIGHT);
        JLabel yLabel = new JLabel("Столбец:  ", JLabel.RIGHT);
        x = new JTextField(2);
        x.setSize(300, 10);
        y = new JTextField(2);
        x.setName("Y");
        strikeButton = new JButton("Огонь!");
        setDisable();
        add(xLabel);
        add(x);
        add(yLabel);
        add(y);
        add(strikeButton);


    }



    public void strikeButton(ButtleField bf){
        strikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int xS = Integer.parseInt(x.getText()) - 1;
                int yS = Integer.parseInt(y.getText()) - 1;
                if (xS >= 0 && xS < 10 && yS >= 0 && yS < 10) {
                    bf.strike(xS, yS);
                    bf.printField();
                    x.setText("");
                    y.setText("");
                }
            }
        });
    }

    public void setEnable(){
        x.setEnabled(true);
        y.setEnabled(true);
        strikeButton.setEnabled(true);
    }

    public void setDisable(){
        x.setEnabled(false);
        y.setEnabled(false);
        strikeButton.setEnabled(false);
    }

}
