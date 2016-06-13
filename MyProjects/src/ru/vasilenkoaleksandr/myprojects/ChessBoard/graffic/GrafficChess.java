package ru.vasilenkoaleksandr.myprojects.ChessBoard.graffic;

import javax.swing.*;
import java.awt.*;

/**
 * Created by admin on 13.06.2016.
 */
public class GrafficChess extends JFrame {
    private static GrafficChess instance = new GrafficChess();

    public static GrafficChess getInstance() {
        return instance;
    }

    private GrafficChess() {
        setLayout(new GridLayout(9, 9));
        setSize(300, 300);
        setTitle("Chess by vasap");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //создание шахматной доски
        add(new JLabel());
        //добавляем литеры
        for (int i = 0; i < 8; i++) {
            char literal = (char) ('A' + i);
            JLabel label = new JLabel("" + literal);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        }

        for (int i = 0, n = 8; i < 8; i++, n--) {
            for (int j = 0; j < 9; j++) {
                if (j == 0) {
                    //добавляем цифры
                    JLabel label = new JLabel(n + "");
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    add(label);
                } else {
                    //добавляем поле
                    char litera = 'A';
                    ChessField chF = new ChessField((int) litera + j-1, n);
                    if ((i + j) % 2 != 0) {
                        chF.setColor(Color.DARK_GRAY);
                    } else {
                        chF.setColor(Color.LIGHT_GRAY);
                    }
                    add(chF);
                }

            }
        }
        setVisible(true);

    }
}
