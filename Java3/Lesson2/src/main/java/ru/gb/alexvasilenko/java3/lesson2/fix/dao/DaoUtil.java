package ru.gb.alexvasilenko.java3.lesson2.fix.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public class DaoUtil {
    public static void createScheme(Connection conn){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS MAMBA_CATALOG " +
                    "(ID    INT  AUTOINCREMENT   PRIMARY KEY     NOT NULL, " +
                    "NAME   TEXT                    NOT NULL, " +
                    "AGE    INT                     NOT NULL," +
                    "SEX    TEXT                    NOT NULL)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
