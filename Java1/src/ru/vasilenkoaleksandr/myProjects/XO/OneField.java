package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 */
public class OneField extends JTextField {

    public OneField(int x ,int y, char ch){
        Font font = new Font("Arial",Font.PLAIN,16);
        setFont(font);

        setPreferredSize(new Dimension(30,30));
        setText(""+ch);
        setEditable(false);

    }

}
