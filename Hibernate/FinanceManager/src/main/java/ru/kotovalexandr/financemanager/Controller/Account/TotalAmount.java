package ru.kotovalexandr.financemanager.Controller.Account;

import ru.kotovalexandr.financemanager.Dao.AccountDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 01.08.2016.
 */
public class TotalAmount {
    private static TotalAmount instance = new TotalAmount();

    public static TotalAmount getInstance() {
        return instance;
    }


    public BigDecimal getTotalAmount(int userID) {
        double result = 0;
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            List<Account> accounts = accountDao.getAll();
            for (Account account : accounts) {
                result += account.getAmount();
            }
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new BigDecimal(result);
    }
}
