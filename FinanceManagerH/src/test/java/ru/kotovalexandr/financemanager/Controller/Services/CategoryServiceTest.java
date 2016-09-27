package ru.kotovalexandr.financemanager.Controller.Services;

import ru.kotovalexandr.financemanager.Dao.CategoryDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 25.08.2016.
 */
public class CategoryServiceTest {

    @Test
    public void addNewCategory() throws Exception {
        Category category = new Category("Test Category");
        CategoryService.getInstance().addOrUpdateCategory(category,0);
        assertNotEquals(-1, category.getId());
        CategoryService.getInstance().remove(new CategoryJList(), category);
    }

    @Test
    public void updateCategory() throws Exception {
        Category category = new Category("Test Category");
        CategoryService.getInstance().addOrUpdateCategory(category,0);
        category.setName("Modify category");
        CategoryService.getInstance().addOrUpdateCategory(category, 1);
        Category modifyCategory = null;
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            modifyCategory = categoryDao.findById(category.getId());
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(category.getName(), modifyCategory.getName());
        CategoryService.getInstance().remove(new CategoryJList(), category);
    }


}