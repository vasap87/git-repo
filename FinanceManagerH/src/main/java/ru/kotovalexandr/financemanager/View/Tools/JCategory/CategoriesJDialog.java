package ru.kotovalexandr.financemanager.View.Tools.JCategory;



import ru.kotovalexandr.financemanager.Controller.Categories.CategoryList;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.View.AddEdit.AddEditCategory;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class CategoriesJDialog extends JDialog implements ActionListener, MouseListener {

    public JButton addButton, editButton, delButton;
    public CategoryJList categoryJList;
    public CategoryJComponent panel;

    public CategoriesJDialog(Dialog owner, String title, boolean modal, CategoryJComponent panel) {
        super(owner, title, modal);
        this.panel = panel;
        setTitle(title);
        setBounds(200, 200, 200, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));
        Box northBox = Box.createHorizontalBox();
        addButton = new JButton("add");
        addButton.setActionCommand("add");
        addButton.addActionListener(this);
        editButton = new JButton("edit");
        editButton.setActionCommand("edit");
        editButton.addActionListener(this);
        delButton = new JButton("del");
        delButton.setActionCommand("del");
        delButton.addActionListener(this);
        northBox.add(addButton);
        northBox.add(Box.createHorizontalStrut(5));
        northBox.add(editButton);
        northBox.add(Box.createHorizontalStrut(5));
        northBox.add(delButton);
        northBox.add(Box.createHorizontalStrut(5));
        add(northBox, BorderLayout.NORTH);

        categoryJList = new CategoryJList();
        CategoryList.getInstance().addObserver(categoryJList);
        CategoryList.getInstance().notifyObservers();
        categoryJList.addMouseListener(this);
        JScrollPane scrollPane = new JScrollPane(categoryJList);
        add(scrollPane, BorderLayout.CENTER);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add": {
                new AddEditCategory(this, "Добавление категории", true).setVisible(true);
                break;
            }
            case "edit": {
                new AddEditCategory(this, "Редактирование категории", true, getCategory()).setVisible(true);
                break;
            }
            case "del": {
                categoryJList.remove(categoryJList.getSelectedIndex());
                break;
            }

        }

    }

    private Category getCategory() {
        if (categoryJList.isSelectionEmpty()) {
            categoryJList.setSelectedIndex(0);
        }
        return (Category) categoryJList.getSelectedValue();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        switch (e.getClickCount()) {
            case 2: {
                panel.setCategory((Category) categoryJList.getSelectedValue());
                this.dispose();
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
