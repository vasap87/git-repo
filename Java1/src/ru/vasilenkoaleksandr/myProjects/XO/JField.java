package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static java.awt.Color.*;
import static java.awt.Color.RED;

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

    public void rePrintField() {
        removeAll();
        for (int i = 0; i < GameField.getInstance().getField().length; i++) {
            for (int j = 0; j < GameField.getInstance().getField().length; j++) {
                OneField of = new OneField(i, j, GameField.getInstance().getField()[i][j], instance);
                //если координаты содержатся в массиве выигрышных - выделяем их другим цветом
                if (FindWin.getInstance().getWinArray() != null) {
                    for (int k = 0; k < FindWin.getInstance().getWinArray().length; k++) {
                        if (FindWin.getInstance().getWinArray()[k].getX() == i && FindWin.getInstance().getWinArray()[k].getY() == j) {
                            of.setBackground(red);
                        }
                    }
                }
                add(of);
            }
        }
        updateUI();
    }
}
