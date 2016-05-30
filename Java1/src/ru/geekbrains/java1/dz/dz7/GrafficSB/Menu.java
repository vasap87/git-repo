package ru.geekbrains.java1.dz.dz7.GrafficSB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by admin on 29.05.2016.
 */
public class Menu extends JPanel {
    private JCheckBox pvc,pvp,cvc;
    public Menu(){
        setLayout(new FlowLayout());
        pvc = new JCheckBox("Игрок против компьютера",false);
        pvp = new JCheckBox("Игрок против игрока",false);
        cvc = new JCheckBox("Компьютер против компьютера",false);
        setEnable();
        add(pvc);
        add(pvp);
        add(cvc);


    }


    public JCheckBox getPvc() {
        return pvc;
    }

    public JCheckBox getPvp() {
        return pvp;
    }

    public JCheckBox getCvc() {
        return cvc;
    }

    public void setDisable(){
        pvc.setEnabled(false);
        pvp.setEnabled(false);
        cvc.setEnabled(false);
    }

    public void setEnable(){
        pvc.setEnabled(true);
        pvp.setEnabled(true);
        cvc.setEnabled(true);
    }
}
