package ru.kotovalexandr.financemanager.View.Tools.JList;



import ru.kotovalexandr.financemanager.Controller.Services.CategoryService;
import ru.kotovalexandr.financemanager.Controller.IObserver;
import ru.kotovalexandr.financemanager.Model.Category;

import javax.swing.*;

/**
 * Created by vasilenko.aleksandr on 01.08.2016.
 */
public class CategoryJList extends JList implements IObserver {

    public CategoryJList() {
        super();
    }

    @Override
    public void handelEvent() {
        CategoryService.getInstance().updateList(this);
    }

    @Override
    public void remove(int index) {
        Category category = (Category) getSelectedValue();
        int answer = JOptionPane.showConfirmDialog(this, "Вы уверены что ходите удалить категорию \"" + category.getName() +"\"",
                "Подтверждение удаление эллемента", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == 0) {
            CategoryService.getInstance().remove(this, category);
        }
    }
}
