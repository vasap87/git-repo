package ru.gb.alexvasilenko.java3.lesson2.fix.dao;



import ru.gb.alexvasilenko.java3.lesson2.fix.Man;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public interface IDaoFactory {
    Connection getConnection() throws SQLException;
    <T extends Man> IGenericDao<T> getManDao(Connection conn);
}
