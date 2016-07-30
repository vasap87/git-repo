package FM.Model;

import FM.Dao.CategoryDao;
import FM.Dao.DBHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class References {
    public static References instance = new References();
    public static References getInstance(){return instance;}
    private References(){
        loadData();
    }

    private void loadData(){
        Connection connection = null;
        try {
            connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            categories = categoryDao.getAll();
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    List<Category> categories;

    public List<Category> getCategories(){
        return categories;
    }

    public void addCategory(Category category){
        categories.add(category);
    }

    public void removeCategory(Category category){
        categories.remove(category);
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            CategoryDao categoryDao = new CategoryDao(connection);
            categoryDao.delete(category);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadData();
    }

}
