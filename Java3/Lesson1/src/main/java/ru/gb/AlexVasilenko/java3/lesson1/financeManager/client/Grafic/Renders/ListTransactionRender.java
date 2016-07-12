package ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Renders;


import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Tools.Account;
import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Tools.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;

/**
 * Created by admin on 12.07.2016.
 */
public class ListTransactionRender extends JPanel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new BorderLayout());
        JPanel upLine = new JPanel();
        upLine.setLayout(new FlowLayout(FlowLayout.RIGHT));
        upLine.add(new JLabel(((Transaction) value).getDesription()));
        Date date = new Date(((Transaction) value).getDateAndTime());
        upLine.add(new JLabel(date.toString()));
        upLine.add(new JLabel(String.valueOf(((Transaction) value).getAmount())));
        add(upLine, BorderLayout.NORTH);
        add(new JLabel((((Transaction) value).isCheckIn())?"пополнение":"снятие"), BorderLayout.CENTER);

        if (isSelected) {
            if (cellHasFocus) {
                this.setBorder(new LineBorder(Color.RED));
            } else {
                this.setBorder(new LineBorder(Color.GREEN));
            }
        } else {
            if (cellHasFocus) {
                this.setBorder(new LineBorder(Color.YELLOW));
            } else {
                this.setBorder(new LineBorder(Color.BLACK));
            }
        }
        return this;
    }
}
