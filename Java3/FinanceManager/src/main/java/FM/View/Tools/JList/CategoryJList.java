package FM.View.Tools.JList;

import FM.Controller.IObserver;
import FM.Dao.AccountDao;
import FM.Dao.CategoryDao;
import FM.Dao.DBHelper;
import FM.Model.Category;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 01.08.2016.
 */
public class CategoryJList extends JList implements IObserver {

    public CategoryJList() {
        super();
    }

    @Override
    public void handelEvent() {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            List<Category> categories = categoryDao.getAll();
            Object arr[] = categories.toArray();
            setListData(arr);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int index) {
        Category category = (Category) getSelectedValue();
        int answer = JOptionPane.showConfirmDialog(this, "Вы уверены что ходите удалить категорию \"" + category.getName() +"\"",
                "Подтверждение удаление эллемента", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == 0) {
            try {
                Connection connection = DBHelper.getInstance().getConnection();
                CategoryDao categoryDao = new CategoryDao(connection);
                categoryDao.delete(category);
                handelEvent();
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
