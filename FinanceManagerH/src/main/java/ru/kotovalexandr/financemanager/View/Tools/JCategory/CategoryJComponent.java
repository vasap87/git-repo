package ru.kotovalexandr.financemanager.View.Tools.JCategory;


import ru.kotovalexandr.financemanager.Model.Category;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class CategoryJComponent extends JPanel implements ActionListener {

    private JDialog dialog;
    private Category category;
    private JTextField categoryName;

    public CategoryJComponent(JDialog dialog) {
        this.dialog = dialog;
        Box box = Box.createHorizontalBox();
        categoryName = new JTextField(20);
        categoryName.setEditable(false);
        box.add(categoryName);
        JButton changeButton = new JButton("...");
        changeButton.setActionCommand("change");
        changeButton.addActionListener(this);
        box.add(changeButton);
        add(box);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "change": {
                new CategoriesJDialog(dialog, "выбор категории", true, this).setVisible(true);
            }
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        categoryName.setText(category.getName());
    }


}
