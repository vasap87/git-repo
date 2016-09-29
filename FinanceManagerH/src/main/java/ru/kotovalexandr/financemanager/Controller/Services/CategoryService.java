package ru.kotovalexandr.financemanager.Controller.Services;


import ru.kotovalexandr.financemanager.Dao.CategoryDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by admin on 24.08.2016.
 */
public class CategoryService {
    private static CategoryService ourInstance = new CategoryService();

    public static CategoryService getInstance() {
        return ourInstance;
    }

    private CategoryService() {
    }


    public void updateList(CategoryJList categoryJList) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            List<Category> categories = categoryDao.getAll();
            Object arr[] = categories.toArray();
            categoryJList.setListData(arr);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(CategoryJList categoryJList, Category category) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            categoryDao.delete(category);
            updateList(categoryJList);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdateCategory(Category category, int operID) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            switch (operID){
                case 0 :{
                    categoryDao.save(category);
                    break;
                }
                case 1 :{
                    categoryDao.update(category);
                    break;
                }
            }
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }
}
