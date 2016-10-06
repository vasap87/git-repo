package ru.kotovalexandr.financemanager.Controller.Services;


import ru.kotovalexandr.financemanager.Hibernate.DAO.CategoryDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.CommonGenDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;

import java.util.List;

/**
 * Created by admin on 24.08.2016.
 */
public final class CategoryService {

    private CategoryService() {
    }


    public static void updateList(CategoryJList categoryJList) {
        CategoryDaoImpl categoryDao = DAOFabric.getCategoryDao();
        List<Category> categories = categoryDao.getAll();
        Object arr[] = categories.toArray();
        categoryJList.setListData(arr);
    }

    public static void remove(CategoryJList categoryJList, Category category) {
            CommonGenDaoImpl commonDao = DAOFabric.getCommonDao();
            commonDao.delete(category);
            updateList(categoryJList);
    }

    public static void addOrUpdateCategory(Category category, int operID) {
            CommonGenDaoImpl commonDao = DAOFabric.getCommonDao();

            switch (operID){
                case 0 :{
                    commonDao.save(category);
                    break;
                }
                case 1 :{
                    commonDao.update(category);
                    break;
                }
            }
    }

    public static Category findByIdService(int id){
        Category category;
        CategoryDaoImpl categoryDao = DAOFabric.getCategoryDao();
        category = categoryDao.findById(id);
        return category;
    }
}
