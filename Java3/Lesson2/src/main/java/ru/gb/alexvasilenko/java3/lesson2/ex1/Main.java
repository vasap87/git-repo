package ru.gb.alexvasilenko.java3.lesson2.ex1;

import javafx.beans.binding.When;

import java.io.*;
import java.sql.*;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 * HomeWork Java3 Lesson2
 * SQL
 *
 */
public class Main {
    private static final String PATH = "src\\sql";

    public static void main(String[] args) {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:myJava3.db");
            //executeSql(connection);//// FIXME: 16.07.2016 не работают скрипты с инсертом need help!

            //first select
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT username FROM users WHERE login IN " +
                    "(" +
                    "SELECT user_to FROM followers WHERE user_from = 'aruizca'" +
                    ");");
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
            }
            System.out.println();

            //second select
            resultSet = statement.executeQuery("SELECT COUNT(*) AS count_users FROM users;");
            if(resultSet.next()) System.out.println(resultSet.getString(1));
            System.out.println();

            //third select
            resultSet = statement.executeQuery("SELECT message, date FROM TWEETS WHERE login IN" +
                    "(" +
                    "SELECT user_to FROM followers WHERE user_from = 'aruizca'" +
                    ")" +
                    " order by date;");
            while (resultSet.next()){
                System.out.printf("%-65s%-15s%n",resultSet.getString(1),resultSet.getString(2));
            }
            System.out.println();

            //fourth select
            resultSet = statement.executeQuery("SELECT LOGIN, COUNT(USER_TO) AS COUNT_FOLLOWERS " +
                    "FROM USERS " +
                    "LEFT OUTER JOIN FOLLOWERS ON USERS.LOGIN = FOLLOWERS.USER_TO " +
                    "GROUP BY LOGIN " +
                    "ORDER BY COUNT_FOLLOWERS DESC;");
            while (resultSet.next()){
                System.out.printf("%-15s%-2s%n",resultSet.getString(1),resultSet.getString(2));
            }
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeSql(Connection connection) {

        File folder = new File(PATH);
        File scripts[] = folder.listFiles();
        FileInputStream in = null;
        for (File script : scripts) {
            //if(script.getPath().endsWith("sql")) {
            // read
            StringBuilder sql = new StringBuilder();
            try {
                in = new FileInputStream(script);
                int a;
                do {
                    a = in.read();
                    sql.append((char) a);
                } while (a != -1);
                in.close();
                //execute
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql.toString());
                statement.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

            }
            sql.append('\n');
            // }
        }

    }
}
