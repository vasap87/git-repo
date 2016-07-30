package FM.Dao;

import FM.Model.Category;
import FM.Model.Transaction;
import FM.Model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Vasilenko on 19.07.2016.
 */
public class TransactionDao implements IGenericDao<Transaction> {

    private Connection connection;
    private static Logger logger = LoggerFactory.getLogger(TransactionDao.class);

    public TransactionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Transaction fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO TRANSACTIONS (ACCOUNT_ID,CATEGORY_ID,ISCHECKIN,DATETIME,AMOUNT,DESCR) VALUES (" +
                    fin.getAccount_id() + ", " + fin.getCategory().getId() + ", " + (fin.isCheckIn() ? 1 : 0) + ", " +
                    fin.getDateAndTime() / 1000 + ", " + fin.getAmount() + ", '" + fin.getDesription() + "');";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("New transaction with sum: " + fin.getAmount() + " added");
                sql = "SELECT ID FROM TRANSACTIONS WHERE ACCOUNT_ID = " + fin.getAccount_id() + " " +
                        "AND DATETIME = " + fin.getDateAndTime() / 1000 + " AND AMOUNT = " + fin.getAmount() + ";";
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    fin.setId(resultSet.getInt("ID"));
                    logger.info("Object Transaction with sum: " + fin.getAmount() + " set id");
                }
            } else {
                logger.error("Object Transaction with sum: " + fin.getAmount() + "not added");
            }
        } catch (SQLException e) {
            logger.error("Error in method save, detail: " + e.getMessage());
        }
    }

    @Override
    public void delete(Transaction fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM TRANSACTIONS WHERE ID = " + fin.getId() + ";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("transaction with sum: " + fin.getAmount() + " deleted");
            } else {
                logger.error("transaction with sum: " + fin.getAmount() + "not deleted");
            }
        } catch (SQLException e) {
            logger.error("Error in method delete, detail: " + e.getMessage());
        }
    }

    @Override
    public void update(Transaction fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "UPDATE TRANSACTIONS SET ACCOUNT_ID = " + fin.getAccount_id() + ", CATEGORY_ID = " + fin.getCategory() +
                    ", ISCHECKIN = " + (fin.isCheckIn() ? 1 : 0) + ", DATETIME = " + fin.getDateAndTime() / 1000 + ", AMOUNT = " +
                    fin.getAmount() + ", DESCR = '" + fin.getDesription() + "' WHERE ID = " + fin.getId() + ";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("transaction with sum: " + fin.getAmount() + " updated");
            } else {
                logger.error("transaction with sum: " + fin.getAmount() + "not updated");
            }
        } catch (SQLException e) {
            logger.error("Error in method update, detail: " + e.getMessage());
        }
    }

    @Override
    public Transaction findById(int id) {
//        try (Statement statement = connection.createStatement()) {
//            String sql = "SELECT * FROM TRANSACTIONS WHERE ID = " + id + ";";
//            ResultSet resultSet = statement.executeQuery(sql);
//            if(resultSet.next()){
//                sql = "SELECT NAME FROM CATEGORIES WHERE ID = " + resultSet.getInt("CATEGORY_ID");
//                ResultSet resultSetCategory = statement.executeQuery(sql);
//                String categoryName = null;
//                if (resultSet.next()) {
//                    categoryName = resultSetCategory.getString(2);
//                }
//                return new Transaction(id,(resultSet.getInt("ISCHECKIN")==1?true:false),resultSet.getInt("ACCOUNT_ID"),
//                        resultSet.getInt("DATETIME")*1000, resultSet.getDouble("AMOUNT"), resultSet.getString("DESCR"),
//                        new Category(categoryName ,resultSet.getInt("CATEGORY_ID")));
//            }
//
//        } catch (SQLException e) {
//            logger.error("Error in method findById, detail: " + e.getMessage());
//        }
        return null;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }

    public List<Transaction> getAllByAccout(Account account) {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_ID = " + account.getId() + ";";
            ResultSet transactionRS = statement.executeQuery(sql);
            while (transactionRS.next()){
                int transactionID = transactionRS.getInt("ID");
                boolean transactionIsCheckin = transactionRS.getInt("ISCHECKIN") == 0 ? false : true;
                long transactionDateTime = (long) transactionRS.getInt("DATETIME") * 1000;
                double transactionAmount = transactionRS.getDouble("AMOUNT");
                String transactionDescr = transactionRS.getString("DESCR");
                int transactionCategoryID = transactionRS.getInt("CATEGORY_ID");
                sql = "SELECT NAME FROM CATEGORIES WHERE ID = " + transactionCategoryID;
                Statement categoryStatement = connection.createStatement();
                ResultSet resultSetCategory = categoryStatement.executeQuery(sql);
                String categoryName = null;
                if (resultSetCategory.next()) {
                    categoryName = resultSetCategory.getString(1);
                }
                categoryStatement.close();
                transactions.add(new Transaction(transactionID, transactionIsCheckin,
                        account.getId(), transactionDateTime, transactionAmount,
                        transactionDescr, new Category(categoryName, transactionCategoryID)));
            }
        } catch (SQLException e) {
            logger.error("Error in method getAllByAccout, detail: " + e.getMessage());
        }
        return transactions;
    }
}
