package ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Renders;


import ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Tools.Account;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by admin on 12.07.2016.
 */
public class ListAccountRender extends JPanel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new BorderLayout());
        JPanel upLine = new JPanel();
        upLine.setLayout(new FlowLayout(FlowLayout.RIGHT));
        upLine.add(new JLabel(((Account) value).getNumber()));
        upLine.add(new JLabel(String.valueOf(((Account) value).getAmount())));
        add(upLine, BorderLayout.NORTH);
        add(new JTextArea(((Account) value).getDescription()), BorderLayout.CENTER);

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
