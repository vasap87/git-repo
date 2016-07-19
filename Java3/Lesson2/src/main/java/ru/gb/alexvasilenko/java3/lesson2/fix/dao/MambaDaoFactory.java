package ru.gb.alexvasilenko.java3.lesson2.fix.dao;


import ru.gb.alexvasilenko.java3.lesson2.fix.Man;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public class MambaDaoFactory implements IDaoFactory {
    private final String URL = "jdbc:sqlite:database.db";
    private final String DRIVER = "org.sqlite.JDBC";

    public MambaDaoFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public <T extends Man> IGenericDao<T> getManDao(Connection conn) {
        return ((IGenericDao<T>) new ManDaoImpl(conn));
    }
}
