package ru.kotovalexandr.financemanager.Dao_old;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.Model.Transaction;
import ru.kotovalexandr.financemanager.Model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Vasilenko on 15.07.2016.
 */
public class AccountDao implements IGenericDao<Account> {
    private Connection connection;
    private int userID;

    private static Logger logger = LoggerFactory.getLogger(AccountDao.class);

    public AccountDao(Connection connection, int userID) {
        this.connection = connection;
        this.userID = userID;
    }

    @Override
    public void save(Account fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "INSERT INTO ACCOUNTS (NUMBER,USER_ID,DESCR) VALUES ('" + fin.getNumber() + "', " + fin.getUserId() + ", '" + fin.getDescription() + "');";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("New account with number: " + fin.getNumber() + " added");
                sql = "SELECT ID FROM ACCOUNTS WHERE NUMBER = '" + fin.getNumber() + "';";
                ResultSet resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    fin.setId(resultSet.getInt("ID"));
                    logger.info("Object Account with number: " + fin.getNumber() + " set id");
                }
            } else {
                logger.error("New account with number: " + fin.getNumber() + "not added");
            }
        } catch (SQLException e) {
            logger.error("Error in method save, detail: " + e.getMessage());
        }
    }

    @Override
    public void delete(Account fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "DELETE FROM ACCOUNTS WHERE ID = " + fin.getId() + ";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("Account with number: " + fin.getNumber() + " deleted");
            } else {
                logger.error("Account with number: " + fin.getNumber() + " not deleted");
            }
        } catch (SQLException e) {
            logger.error("Error in method delete, detail: " + e.getMessage());
        }
    }

    @Override
    public void update(Account fin) {
        try (Statement statement = connection.createStatement()) {
            String sql = "UPDATE ACCOUNTS SET NUMBER = '" + fin.getNumber() + "', DESCR = '" + fin.getDescription() + "' " + " WHERE ID = " + fin.getId() + ";";
            if (statement.executeUpdate(sql) > 0) {
                logger.info("Account with number: " + fin.getNumber() + " updated");
            } else {
                logger.error("Account with number: " + fin.getNumber() + " not updated");
            }
        } catch (SQLException e) {
            logger.error("Error in method update, detail: " + e.getMessage());
        }
    }

    @Override
    public Account findById(int id) {
        try (Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM ACCOUNTS WHERE ID = " + id + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_ID = " + id + ";";
                ResultSet resultSetTR = statement.executeQuery(sql);
                List<Transaction> transactions = new ArrayList();
                while (resultSetTR.next()) {
                    sql = "SELECT NAME FROM CATEGORIES WHERE ID = " + resultSetTR.getInt("CATEGORY_ID");
                    ResultSet resultSetCategory = statement.executeQuery(sql);
                    String categoryName = null;
                    if (resultSetCategory.next()) {
                        categoryName = resultSetCategory.getString(2);
                    }
                    transactions.add(new Transaction(9999, (resultSetTR.getInt("ISCHECKIN") == 0 ? false : true),
                            new Account(), resultSetTR.getInt("DATETIME") * 1000, new BigDecimal(resultSetTR.getDouble("AMOUNT")),
                            resultSetTR.getString("DESCR"), new Category(categoryName, resultSetTR.getInt("CATEGORY_ID"))));
                }
                logger.info("returned object Account with ID = " + id);
                return new Account(id, resultSet.getString("NUMBER"), new User(), resultSet.getString("DESCR"));
            }
        } catch (SQLException e) {
            logger.error("Error in method findById, detail: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList();
        try {
            Statement accountStatement = connection.createStatement();
            String sql = "SELECT * FROM ACCOUNTS WHERE USER_ID = " + userID + ";";
            ResultSet resultSet = accountStatement.executeQuery(sql);
            while (resultSet.next()) {
                int accountID = resultSet.getInt("ID");
                String accountNumber = resultSet.getString("NUMBER");
                String accountDescr = resultSet.getString("DESCR");
                Statement transactionStatement = connection.createStatement();
                sql = "SELECT * FROM TRANSACTIONS WHERE ACCOUNT_ID = " + accountID + ";";
                ResultSet resultSetTR = transactionStatement.executeQuery(sql);
                List<Transaction> transactions = new ArrayList();
                while (resultSetTR.next()) {
                    int transactionID = resultSetTR.getInt("ID");
                    boolean transactionIsCheckin = (resultSetTR.getInt("ISCHECKIN") == 0 ? false : true);
                    long transactionDateTime = (long) resultSetTR.getInt("DATETIME") * 1000;
                    BigDecimal transactionAmount = new BigDecimal(resultSetTR.getDouble("AMOUNT"));
                    String transactionDescr = resultSetTR.getString("DESCR");
                    int transactionCategoryID = resultSetTR.getInt("CATEGORY_ID");
                    sql = "SELECT NAME FROM CATEGORIES WHERE ID = " + transactionCategoryID;
                    Statement categoryStatement = connection.createStatement();
                    ResultSet resultSetCategory = categoryStatement.executeQuery(sql);
                    String categoryName = null;
                    if (resultSetCategory.next()) {
                        categoryName = resultSetCategory.getString(1);
                    }
                    categoryStatement.close();
                    transactions.add(new Transaction(transactionID, transactionIsCheckin,
                            new Account(), transactionDateTime, transactionAmount,
                            transactionDescr, new Category(categoryName, transactionCategoryID)));
                }
                transactionStatement.close();
                accounts.add(new Account(accountID, accountNumber, new User(), accountDescr));
            }
            accountStatement.close();
        } catch (SQLException e) {
            logger.error("Error in method getAll, detail: " + e.getMessage());
            e.printStackTrace();
        }
        return accounts;
    }
}
