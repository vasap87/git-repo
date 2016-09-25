package ru.kotovalexandr.financemanager.View.Tools.JList.Renders;


import ru.kotovalexandr.financemanager.Model.Transaction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 12.07.2016.
 */
public class ListTransactionRender extends JTextPane implements ListCellRenderer<Transaction> {


    @Override
    public Component getListCellRendererComponent(JList<? extends Transaction> list, Transaction value, int index, boolean isSelected, boolean cellHasFocus) {
        StringBuilder sb = new StringBuilder();
        Date date = new Date(value.getDateAndTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        sb.append("<html><head></head><body><b>Сумма: " + String.valueOf(value.getAmount()) + "</b><br><i>операция: </i>" + (value.isCheckIn()?"Пополнение":"Снятие") + "<br>" +
                "<i>описание: </i>" + value.getDesription() + "<br>" +
                "<i>дата: </i>" + dateFormat.format(date) + "    <i>категория:</i>"+value.getCategory().getName()+"</body></html>");
        setContentType("text/html");
        setEditable(false);
        setText(sb.toString());
        setBorder(new LineBorder(Color.black, 1));
        if (isSelected) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        return this;
    }
}
