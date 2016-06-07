package ru.vasilenkoaleksandr.myprojects.BlackJack;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public abstract class JCard extends JLabel {
    private Font font = new Font("Arial", Font.PLAIN, 26);

    public JCard() {
        setFont(font);
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setVisible(true);
    }

}
