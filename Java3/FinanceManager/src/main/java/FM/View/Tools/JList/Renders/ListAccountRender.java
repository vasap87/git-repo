package FM.View.Tools.JList.Renders;


import FM.Model.Account;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Created by admin on 12.07.2016.
 */
public class ListAccountRender extends JTextPane implements ListCellRenderer<Account> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Account> list, Account value, int index, boolean isSelected, boolean cellHasFocus) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head><body><b>№" + value.getNumber() + "</b><br><i>баланс:</i>" + String.valueOf(value.getAmount()) + "<br>" + "<i>описание:</i>" + value.getDescription() + "<br>" +
                "<i>проводок:</i>" + value.getTransactionList().size() + "</body></html>");
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
