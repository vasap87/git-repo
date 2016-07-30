package FM.Dao;

import FM.Model.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class CategoryDao implements IGenericDao<Category> {

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);
    private Connection connection;

    public CategoryDao(Connection connection){
        this.connection = connection;
    }

    @Override
    public void save(Category fin) {
        try (Statement statement = connection.createStatement()) {
            String sql;
            if(fin.getId()>0){
                sql = "UPDATE CATEGORIES SET NAME = '"+fin.getName()+"';";
                if(statement.executeUpdate(sql)>0){
                    logger.info("Category: " + fin.getName() + " updated");
                }
            }else{
                sql = "INSERT INTO CATEGORIES (NAME) VALUES ("+fin.getName()+");";
                if(statement.executeUpdate(sql)>0){
                    sql = "SELECT ID FROM CATEGORIES WHERE NAME = '"+ fin.getName() +"';";
                    ResultSet resultSet = statement.executeQuery(sql);
                    if(resultSet.next()){
                        fin.setId(resultSet.getInt(1));
                    }
                    logger.info("Category: " + fin.getName() + " added with id: "+ fin.getId());
                }
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
                logger.info("Category : " + fin.getName() + " deleted");
            } else {
                logger.error("Category: " + fin.getName() + "not deleted");
            }
        } catch (SQLException e) {
            logger.error("Error in method delete, detail: " + e.getMessage());
        }
    }

    @Override
    public void update(Category fin) {

    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM CATEGORIES";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                categories.add(new Category(resultSet.getString("NAME"), resultSet.getInt("ID")));
            }
        } catch (SQLException e) {
            logger.info("Error in method delete, getAll: " + e.getMessage());
        }
        return categories;
    }
}
