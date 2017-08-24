package ru.kotovalex.CareHire.DatePicker;

import javax.swing.*;

/**
 * Created by vasilenko.aleksandr on 06.07.2016.
 */
public class DatePickerPanel extends JPanel {
    public DatePickerPanel() {
        BoxLayout layout = new BoxLayout(this,BoxLayout.X_AXIS);
        setLayout(layout);
        JTextField dateField = new JTextField();
        JButton changeDateButton = new JButton("D");
        add(dateField);
        add(changeDateButton);

    }
}
