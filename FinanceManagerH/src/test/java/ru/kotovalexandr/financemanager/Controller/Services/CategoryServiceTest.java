package ru.kotovalexandr.financemanager.Controller.Services;

import ru.kotovalexandr.financemanager.Model.Category;
import org.junit.Test;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;


import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 25.08.2016.
 */
public class CategoryServiceTest {

    @Test
    public void addNewCategory() throws Exception {
        Category category = new Category("Test Category");
        CategoryService.addOrUpdateCategory(category,0);
        assertNotEquals(-1, category.getId());

        CategoryService.remove(new CategoryJList(), category);
    }

    @Test
    public void updateCategory() throws Exception {
        Category category = new Category("Test Category");
        CategoryService.addOrUpdateCategory(category,0);
        category.setName("Modify category");
        CategoryService.addOrUpdateCategory(category, 1);

        Category modifyCategory = CategoryService.findByIdService(category.getId());

        assertEquals(category.getName(), modifyCategory.getName());
        CategoryService.remove(new CategoryJList(), category);
    }


}