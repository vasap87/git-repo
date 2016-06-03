package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 */
public class JField extends JPanel {

    private static JField instance;

    private JField() {
        setLayout(new GridLayout(GameField.SIZE, GameField.SIZE));
        rePrintField();
        setVisible(true);

    }

    public static JField getInstance() {
        if (instance == null) {
            instance = new JField();
        }
        return instance;
    }

    public void rePrintField(){
        removeAll();
        for (int i = 0; i < GameField.getInstance().getField().length; i++) {
            for (int j = 0; j < GameField.getInstance().getField().length; j++) {
                add(new OneField(i, j, GameField.getInstance().getField()[i][j], instance));
            }
        }
        updateUI();
    }
}
