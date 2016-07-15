package ru.gb.alexvasilenko.java3.lesson2.FinManager.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 * <p>
 * Object of this class can exist in single instance.
 * Metod authorisation @return {@link String} with authorizated login,
 * else return <b>null</b>.
 * Method registration @return {@link String} with login of new user,
 * else return <b>null</b>.
 */
public class SignInOn {
    private static SignInOn instance = new SignInOn();
    private static Logger logger = LoggerFactory.getLogger(SignInOn.class);

    /**
     * Method @return the instanse of class {@link SignInOn}
     * */
    public static SignInOn getInstance() {
        return instance;
    }

    //closed construstor
    private SignInOn() {

    }

    /**
     * Method to authorisation user
     * @param connection {@link Connection}
     * @param login {@link String}
     * @param password {@link String}
     *
     * @return String of login user, else <b>null</b>
     * */
    public String authorisation(Connection connection, String login, String password) {
        String authLogin = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT LOGIN FROM USERS WHERE LOGIN ='" + login + "' AND PASS = '" + password + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                authLogin = resultSet.getString(1);
                logger.info("User with login " + authLogin + " auth complite");
            }
        } catch (SQLException e) {
            logger.error("Error in method authorisation, detail: " + e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error at finally part of method authorisation, detail: " + e.getMessage());
            }
        }
        return authLogin;
    }

    /**
     * Method to registration new user
     * @param connection {@link Connection}
     * @param login {@link String}
     * @param password {@link String}
     *
     * @return String of login new user, else <b>null</b>
     * */
    public String registration(Connection connection, String login, String password) {
        String newLogin = null;
        Statement statement = null;
        try  {
            statement = connection.createStatement();
            String sql = "SELECT LOGIN FROM USERS WHERE LOGIN ='" + login + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return null;
            } else{
                sql = "INSERT INTO USERS (LOGIN,PASS) VALUES ('" + login + "', '" + password + "');";
                int result = statement.executeUpdate(sql);
                if (result == 1) {
                    newLogin = login;
                    logger.info("New user with login " + newLogin + " is register");
                }
            }

        } catch (SQLException e) {
            logger.error("Error in method registration, detail: " + e.getMessage());
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Error at finally part of method registration, detail: " + e.getMessage());
            }
        }
        return newLogin;
    }
}
