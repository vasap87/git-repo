package FM.View.AddEdit;

import FM.Controller.Categories.CategoryList;
import FM.Dao.CategoryDao;
import FM.Dao.DBHelper;
import FM.Model.Category;
import FM.View.Tools.JCategory.CategoriesJDialog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by vasilenko.aleksandr on 28.07.2016.
 */
public class AddEditCategory extends JDialog implements ActionListener {

    private JTextArea name;
    private Category category;
    private CategoriesJDialog categoriesJDialog;
    int operID;

    public AddEditCategory(CategoriesJDialog categoriesJDialog, String title, boolean modal) {
        super(categoriesJDialog, title, modal);
        this.categoriesJDialog = categoriesJDialog;
        operID = 0;
        setMinimumSize(new Dimension(200,100));
        setResizable(true);
        Box mainBox = Box.createVerticalBox();
        Box firstRow = Box.createHorizontalBox();
        firstRow.add(new JLabel("Описание"));
        firstRow.add(Box.createVerticalStrut(10));
        name = new JTextArea(3,20);
        name.setLineWrap(true);
        name.setBorder(new LineBorder(Color.GRAY,1));
        name.setWrapStyleWord(true);
        firstRow.add(name);
        mainBox.add(firstRow);
        mainBox.add(Box.createHorizontalStrut(5));
        Box secondRow = Box.createHorizontalBox();
        JButton okButton = new JButton("Сохранить");
        okButton.setActionCommand("save");
        okButton.addActionListener(this);
        secondRow.add(okButton);
        secondRow.add(Box.createVerticalStrut(10));
        JButton cancelButton = new JButton("Отмена");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        secondRow.add(cancelButton);
        mainBox.add(secondRow);
        setContentPane(mainBox);


    }

    public AddEditCategory(CategoriesJDialog categoriesJDialog, String s, boolean b, Category category) {
        this(categoriesJDialog, s, b);
        this.category = category;
        this.categoriesJDialog = categoriesJDialog;
        operID = 1;
        name.setText(category.getName());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "save" : {
                try {
                    Connection connection = DBHelper.getInstance().getConnection();
                    CategoryDao categoryDao = new CategoryDao(connection);
                    switch (operID){
                        case 0 :{
                            categoryDao.save(new Category(name.getText()));
                            break;
                        }
                        case 1 :{
                            category.setName(name.getText());
                            categoryDao.update(category);
                            break;
                        }
                    }
                    CategoryList.getInstance().notifyObservers();
                    this.dispose();
                    DBHelper.getInstance().closeConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                break;
            }
            case "cancel" : {
                this.dispose();
                break;
            }
        }

    }
}
