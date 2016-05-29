package ru.geekbrains.java1.dz.dz7.GrafficSB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 29.05.2016.
 */
public class Menu extends JPanel {
    private int result;
    private JCheckBox pvc,pvp,cvc;
    public Menu(){
        setLayout(new FlowLayout());
        pvc = new JCheckBox("Игрок против компьютера",true);
        pvp = new JCheckBox("Игрок против игрока",false);
        cvc = new JCheckBox("Компьютер против компьютера",false);
        add(pvc);
        add(pvp);
        add(cvc);

        pvc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pvc.isSelected()){
                    result=1;
                    pvp.setSelected(false);
                    cvc.setSelected(false);
                }
            }
        });
        pvp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pvp.isSelected()){
                    result=2;
                    pvc.setSelected(false);
                    cvc.setSelected(false);
                }
            }
        });
        cvc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cvc.isSelected()){
                    result=2;
                    pvp.setSelected(false);
                    pvc.setSelected(false);
                }
            }
        });
    }

    public int getResult() {
        return result;
    }
}
