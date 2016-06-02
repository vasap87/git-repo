package ru.geekbrains.java1.dz.dz7.GrafficSB_v2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 01.06.2016.
 */
public class CompBF extends SimpleButtleField {
    private static final char HIT = 'X';
    private static final char MIS = '*';
    private static final char WATER = '~';
    private static final char SHIP = 'O';
    private static char[][] shipsField;

    public CompBF(boolean gameTypeOne) {
        super(gameTypeOne);
    }

    @Override
    public void printField(char[][] emptyField, boolean gameType) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        for (int i = 0; i < getSIZE() + 1; i++) {
            gbc.gridx = i;
            JLabel numColumn = new JLabel();
            numColumn.setHorizontalAlignment(SwingConstants.CENTER);
            if (i != 0) numColumn.setText("" + i);
            else numColumn.setText("\\");
            add(numColumn, gbc);
        }
        for (int i = 0; i < getSIZE(); i++) {
            gbc.gridy = i + 1;

            for (int j = -1; j < getSIZE(); j++) {
                gbc.gridx = j + 1;
                if (j == -1) {
                    JLabel numRow = new JLabel((i + 1) + "");
                    numRow.setVerticalAlignment(SwingConstants.CENTER);
                    numRow.setHorizontalAlignment(SwingConstants.CENTER);
                    add(numRow, gbc);
                } else {
                    JTextField tf = new JTextField(getShipsField()[i][j]+"");
                    tf.setEditable(false);
                    tf.setHorizontalAlignment(SwingConstants.CENTER);
                    tf.setPreferredSize(new Dimension(30,30));
                    add(tf, gbc);
                }

            }
        }
        this.shipsField=getShipsField();
    }

    public static boolean compStrike(int x, int y){
        if (shipsField[x][y] == WATER) {
            shipsField[x][y] = MIS;
            //isHit = false;
            return true;
        }
        if (shipsField[x][y] == SHIP) {
            shipsField[x][y] = HIT;
            //isHit = true;
            return true;
        }
        if (shipsField[x][y] == HIT) {
            //isHit = false;
            return false;
        }
        if (shipsField[x][y] == MIS) {
            //isHit = false;
            return false;
        }

        return true;
    }

    private void updateGF(){
        this.updateUI();
    }
}

