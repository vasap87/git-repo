package ru.vasilenkoaleksandr.myprojects.ListCase;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalBorders;
import java.awt.*;

/**
 * Created by vasilenko.aleksandr on 23.06.2016.
 */
public class SimpleCaseRender extends JPanel implements ListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        setLayout(new BorderLayout());
        JPanel upLine = new JPanel();
            upLine.setLayout(new FlowLayout(FlowLayout.CENTER));
            upLine.add(new JLabel(((SimpleCase) value).getName()));
            upLine.add(new JLabel(((SimpleCase) value).getDate()));
        add(upLine, BorderLayout.NORTH);
        add(new JTextArea(((SimpleCase) value).getDescription()), BorderLayout.CENTER);

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
