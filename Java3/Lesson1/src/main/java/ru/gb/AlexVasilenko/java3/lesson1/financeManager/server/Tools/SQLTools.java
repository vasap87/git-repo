package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server.Tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by vasilenko.aleksandr on 28.06.2016.
 * Класс для создания подключения к бд и выполнения селектов из базы
 */

public class SQLTools {
    //переменная инстанса
    private static SQLTools instance = new SQLTools();
    //переменные для создания подключения к БД
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static final String PATH = "src\\main\\java\\ru\\gb\\AlexVasilenko\\java3\\lesson1\\financeManager\\server\\Tools\\sqlprop.properties";

    public static SQLTools getInstance() {
        return instance;
    }

    private SQLTools() {

    }

    public void setConnection() {
        Properties dbProp = new Properties();
        try {
            InputStream is = Files.newInputStream(Paths.get(PATH));
            dbProp.load(is);
            String driver = dbProp.getProperty("jdbc.driver");
            String url = dbProp.getProperty("jdbc.url");
            String user = dbProp.getProperty("jdbc.user");
            String pass = dbProp.getProperty("jdbc.pass");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            //проверка, нужно ли создавать таблицы в БД
            preparedStatement = connection.prepareStatement("select count(relname) as count from pg_class where relname='fin_users'");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (Integer.parseInt(resultSet.getString("count")) == 0) {
                    firstConnection();
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось найти библиотеку с драйвером: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Не удалось установить соединение с БД: " + e.getMessage());
            e.printStackTrace();
        } catch (InvalidPathException e) {
            System.out.println("Не удалось найти файл настроек: " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDisconnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println("не удалось отключиться от БД");
        }
    }

    public boolean authorisationByLoginAndPassword(String login, String password) {
        String nickname = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT login FROM fin_users WHERE login = ? AND password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nickname = resultSet.getString("login");
                if (!nickname.isEmpty()) return true;
            }
        } catch (SQLException e) {
            System.out.println("Не возможно выполнить селект из базы, причина: " + e.getMessage());
        }
        return false;
    }

    public boolean registerNewUser(String login, String password) {
        try {
            preparedStatement = connection.prepareStatement("select count(login) as count from fin_users where login = ? and password = ?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (Integer.parseInt(resultSet.getString("count")) == 0) {
                    preparedStatement = connection.prepareStatement("INSERT INTO fin_users (login, password) VALUES (?,?)");
                    preparedStatement.setString(1, login);
                    preparedStatement.setString(2, password);
                    preparedStatement.execute();
                    return true;
                } else return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка регистрации пользователя. Подробенее: " + e.getMessage());
        }
        return false;
    }

    public List getAccountsByUserName(String username) {
        try {
            preparedStatement = connection.prepareStatement("SELECT id, number, description FROM account WHERE user_id IN (SELECT id FROM fin_users WHERE login = '" + username + "')");
            ResultSet accountSet = preparedStatement.executeQuery();
            List accountList = new ArrayList<Account>();
            while (accountSet.next()) {
                int accountID = accountSet.getInt(1);
                String number = accountSet.getString(2);
                String description = accountSet.getString(3);
                preparedStatement = connection.prepareStatement("SELECT * FROM transaction_log WHERE acount_id = ?");
                preparedStatement.setString(1, String.valueOf(accountID));
                ResultSet transactionSet = preparedStatement.executeQuery();
                List transactionList = new ArrayList<Transaction>();
                while (transactionSet.next()) {
                    int id = transactionSet.getInt(1);
                    String account_num = transactionSet.getString(2);
                    boolean isCheckIn = transactionSet.getBoolean(3);
                    long dateAndTime = transactionSet.getLong(4);
                    double amount = transactionSet.getDouble(5);
                    String desription = transactionSet.getString(6);
                    transactionList.add(new Transaction(id, account_num, isCheckIn, dateAndTime, amount, desription));
                }
                accountList.add(new Account(number, username, description, transactionList));
            }
            return accountList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<Account>();
    }

    public void newTransaction(Transaction transaction, int amount) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO transaction_log (acount_id, ischeckin, dateandtime, amount, description) VALUES (?,?,?,?,?)");
            preparedStatement.setString(1, String.valueOf(amount));
            preparedStatement.setString(2, String.valueOf(transaction.isCheckIn()));
            preparedStatement.setString(3, String.valueOf(transaction.getDateAndTime()));
            preparedStatement.setString(4, String.valueOf(transaction.getAmount()));
            preparedStatement.setString(5, transaction.getDesription());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка добавления транзакции. Подробенее: " + e.getMessage());
        }
    }

    private void firstConnection() {
        try {
            //создание последовательностей
            preparedStatement = connection.prepareStatement("CREATE SEQUENCE user_ids;\n" +
                    "CREATE SEQUENCE transaction_ids;\n" +
                    "CREATE SEQUENCE account_ids;");
            preparedStatement.execute();
            //создание таблицы пользователей
            preparedStatement = connection.prepareStatement("CREATE TABLE fin_users" + "(" +
                    "  id integer NOT NULL DEFAULT nextval('user_ids'::regclass)," +
                    "  login character varying(100) NOT NULL," +
                    "  password character varying(100) NOT NULL," +
                    "  CONSTRAINT fin_users_pkey PRIMARY KEY (id )," +
                    "  CONSTRAINT fin_users_login_key UNIQUE (login )" + ")");
            preparedStatement.execute();
            //создание таблицы транзакций
            preparedStatement = connection.prepareStatement("CREATE TABLE transaction_log" + "(" +
                    "  id integer NOT NULL DEFAULT nextval('transaction_ids'::regclass),\n" +
                    "  acount_id integer NOT NULL, \n" +
                    "  ischeckin boolean NOT NULL,\n" +
                    "  dateandtime bigserial NOT NULL,\n" +
                    "  amount numeric NOT NULL,\n" +
                    "  description character varying(250),\n" +
                    "  CONSTRAINT transaction_log_pkey PRIMARY KEY (id, acount_id)" + ")");
            preparedStatement.execute();
            //создание таблицы счетов
            preparedStatement = connection.prepareStatement("CREATE TABLE account" + "(" +
                    "  id integer NOT NULL DEFAULT nextval('account_ids'::regclass),\n" +
                    "  number character varying(100),\n" +
                    "  user_id integer NOT NULL,\n" +
                    "  description character varying(250),\n" +
                    "  CONSTRAINT account_pkey PRIMARY KEY (id , user_id ),\n" +
                    "  CONSTRAINT account_id_key UNIQUE (id )" + ")");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
