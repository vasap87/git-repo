package ru.geekbrains.java1.dz.dz8.AlexVasilenko;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vasilenko.aleksandr on 31.05.2016.
 */
public class NumButtons extends JButton {


    public NumButtons(String num, JTextField label) {
        super();
        setText(" " + num);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder(label.getText());
                sb.append(num);
                label.setText(sb.toString());
            }
        });
    }

}
