package ru.kotovalexandr.financemanager.Dao_old;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * Created by admin on 24.08.2016.
 */
public class DBHelperTest {

    @Test
    public void createConnectionTest() throws Exception{
        Connection connection = DBHelper.getInstance().getConnection();
        assertNotNull(connection);
        DBHelper.getInstance().closeConnection();
    }


}