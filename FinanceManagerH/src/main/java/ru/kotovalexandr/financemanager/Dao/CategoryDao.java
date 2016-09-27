package ru.kotovalexandr.financemanager.Dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kotovalexandr.financemanager.Model.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class CategoryDao implements IGenericDao<Category> {

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);
    private Connection connection;

    public CategoryDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Category fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO CATEGORIES (NAME) VALUES ('" + fin.getName() + "');";
            if (statement.executeUpdate(sql) > 0) {
                sql = "SELECT ID FROM CATEGORIES WHERE NAME = '" + fin.getName() + "';";
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    fin.setId(resultSet.getInt(1));
                }
                logger.info("Category: " + fin.getName() + " added with id: " + fin.getId());
            }
        } catch (SQLException e) {
            logger.error("Error in method save, detail: " + e.getMessage());
        }
    }

    @Override
    public void delete(Category fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM CATEGORIES WHERE ID = " + fin.getId() + ";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("Category: " + fin.getName() + " deleted");
            } else {
                logger.error("Category: " + fin.getName() + " not deleted");
            }
        } catch (SQLException e) {
            logger.error("Error in method delete, detail: " + e.getMessage());
        }
    }

    @Override
    public void update(Category fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "UPDATE CATEGORIES SET NAME ='" + fin.getName() + "' WHERE ID = "+fin.getId()+";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("Category: " + fin.getName() + " is updated");
            }
        } catch (SQLException e) {
            logger.error("Error in method update, detail: " + e.getMessage());
        }
    }

    @Override
    public Category findById(int id) {
        Category category = null;
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT ID, NAME FROM CATEGORIES WHERE ID = "+id+";";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                category = new Category(resultSet.getString("NAME"), id);
            }
        } catch (SQLException e) {
            logger.error("Error in method update, detail: " + e.getMessage());
        }
        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM CATEGORIES";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getString("NAME"), resultSet.getInt("ID")));
            }
        } catch (SQLException e) {
            logger.info("Error in method delete, getAll: " + e.getMessage());
        }
        return categories;
    }
}
