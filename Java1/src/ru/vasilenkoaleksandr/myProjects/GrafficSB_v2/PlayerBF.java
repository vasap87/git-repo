package ru.vasilenkoaleksandr.myProjects.GrafficSB_v2;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 01.06.2016.
 */
public class PlayerBF extends SimpleButtleField {
    private static final char HIT = 'X';
    private static final char MIS = '*';
    private CompBF compBF;

    public PlayerBF(boolean gameTypeOne, CompBF compButtleField) {
        super(gameTypeOne);
        if (compButtleField!=null){
            compBF=compButtleField;
        }
    }


    public void printField(char[][] emptyField, boolean gameTypeOne) {
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
                    UserFieldOne fo = new UserFieldOne(i, j, emptyField[i][j] + "", emptyField, getShipsField(), MIS, HIT, gameTypeOne, compBF);
                    add(fo, gbc);
                }

            }
        }
    }
}
