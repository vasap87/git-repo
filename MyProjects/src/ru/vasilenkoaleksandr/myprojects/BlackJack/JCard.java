package ru.vasilenkoaleksandr.myprojects.BlackJack;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public abstract class JCard extends JLabel {
    private Font font = new Font("Arial", Font.PLAIN, 26);

    public JCard(String name) {
        setFont(font);
        setText(name);
        setBorder(new LineBorder(Color.BLACK));
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setVisible(true);
    }

}
