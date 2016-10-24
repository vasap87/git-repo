package ru.kotovalexandr.financemanager.Dao_old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 * <p>
 * Object of this class can exist in single instance.
 * Method getConnection return instance of class {@link Connection}.
 * If this is the first connection to database, @method firstConnection
 * create structure.
 *
 * @see Connection
 * @see SQLException
 */
public class DBHelper {
    private static DBHelper instance = new DBHelper();
    private final String DRIVER = "org.sqlite.JDBC";
    private final String URL = "jdbc:sqlite:finance.db";
    private Logger logger = LoggerFactory.getLogger(DBHelper.class);
    private Connection connection = null;

    /**
     * Method @return single instance of class {@link DBHelper}
     */
    public static DBHelper getInstance() {
        return instance;
    }

    //Close constructor
    private DBHelper() {
        try {
            Class.forName(DRIVER);
            firstConnection();
        } catch (ClassNotFoundException e) {
            logger.error("Driver: " + DRIVER + " not found.");
        }
    }

    /**
     * Method @return {@link Connection}
     */
    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(URL);
        return connection;
    }

    /**
     * Method close connection
     */
    public void closeConnection() throws SQLException, NullPointerException {
        connection.close();
    }

    /**
     * If database not exists the tables which we used,
     * we create it in this method
     */
    private void firstConnection() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT count(*) as count FROM sqlite_master WHERE type='table' AND name in ('USERS','ACCOUNTS','TRANSACTIONS');";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                //if no results - create tables
                if (resultSet.getInt(1) == 0) {
                    //create users table
                    sql = "CREATE TABLE USERS (" +
                            "ID INTEGER PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT," +
                            "LOGIN TEXT NOT NULL UNIQUE ON CONFLICT FAIL," +
                            "PASS TEXT NOT NULL);";
                    statement.execute(sql);
                    //create accounts table
                    sql = "CREATE TABLE ACCOUNTS (" +
                            "ID INTEGER NOT NULL PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT," +
                            "NUMBER TEXT NOT NULL UNIQUE ON CONFLICT FAIL," +
                            "USER_ID INTEGER REFERENCES USERS (ID) ON DELETE NO ACTION," +
                            "DESCR TEXT);";
                    statement.execute(sql);
                    //create categories table
                    sql = "CREATE TABLE CATEGORIES (" +
                            "ID INTEGER NOT NULL PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT," +
                            "NAME TEXT NOT NULL);";
                    statement.execute(sql);
                    //инсерт категорий
                    sql = "INSERT INTO CATEGORIES(NAME) VALUES ('еда');";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO CATEGORIES(NAME) VALUES ('транспорт');";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO CATEGORIES(NAME) VALUES ('обучение');";
                    statement.executeUpdate(sql);
                    sql = "INSERT INTO CATEGORIES(NAME) VALUES ('развлечение');";
                    statement.executeUpdate(sql);
                    //create transactions table
                    sql = "CREATE TABLE TRANSACTIONS (" +
                            "ID INTEGER NOT NULL PRIMARY KEY ON CONFLICT FAIL AUTOINCREMENT," +
                            "ACCOUNT_ID INTEGER REFERENCES ACCOUNTS (ID) ON DELETE NO ACTION," +
                            "CATEGORY_ID INTEGER REFERENCES CATEGORIES (ID) ON DELETE NO ACTION," +
                            "ISCHECKIN INTEGER NOT NULL," +
                            "DATETIME INTEGER DEFAULT (CURRENT_TIMESTAMP)," +
                            "AMOUNT NUMBER NOT NULL," +
                            "DESCR TEXT);";
                    statement.execute(sql);
                }
            }
            statement.close();
        } catch (SQLException e) {
            logger.error("Error in method firstConnection, detail: " + e.getMessage());
        }
    }
}
