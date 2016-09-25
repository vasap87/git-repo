package ru.kotovalexandr.financemanager.Dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public int authorisation(Connection connection, String login, String password) {
        int authLoginID = 0;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "SELECT ID FROM USERS WHERE LOGIN ='" + login + "' AND PASS = '" + hashing(password) + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                authLoginID = resultSet.getInt(1);
                logger.info("User with login " + login + " is authorized");
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
        return authLoginID;
    }

    /**
     * Method to registration new user
     * @param connection {@link Connection}
     * @param login {@link String}
     * @param password {@link String}
     *
     * @return String of login new user, else <b>null</b>
     * */
    public int registration(Connection connection, String login, String password) {
        int newLoginID = 0;
        Statement statement = null;
        try  {
            statement = connection.createStatement();
            String sql = "SELECT ID FROM USERS WHERE LOGIN ='" + login + "';";
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return 0;
            } else{
                sql = "INSERT INTO USERS (LOGIN,PASS) VALUES ('" + login + "', '" + hashing(password) + "');";
                int result = statement.executeUpdate(sql);
                if (result == 1) {
                    sql = "SELECT ID FROM USERS WHERE LOGIN ='" + login + "' AND PASS = '" + hashing(password) + "';";
                    resultSet = statement.executeQuery(sql);
                    if(resultSet.next()){
                        newLoginID = resultSet.getInt(1);
                        logger.info("New user with login " + login + " is registered");
                    }

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
        return newLoginID;
    }

    /**
     * Method hashing string in SHA-224 algorithm
     * @return String of hashig string
     * */
    private String hashing(String text) {
        MessageDigest md = null;
        byte[] hashingBytes = new byte[0];
        try {
            md = MessageDigest.getInstance("SHA-224");
            md.reset();
            md.update(text.getBytes());
            hashingBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bi = new BigInteger(1, hashingBytes);
        String sha_224 = bi.toString(16);
        while (sha_224.length() < 32) {
            sha_224 = "0" + sha_224;
        }
        return sha_224;
    }
}
