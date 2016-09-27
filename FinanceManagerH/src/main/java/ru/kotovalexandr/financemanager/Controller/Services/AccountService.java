package ru.kotovalexandr.financemanager.Controller.Services;



import ru.kotovalexandr.financemanager.Dao.AccountDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.View.Tools.JList.AccountJList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 24.08.2016.
 */
public class AccountService {
    private static AccountService ourInstance = new AccountService();

    public static AccountService getInstance() {
        return ourInstance;
    }

    private AccountService() {
    }

    public void updateList(AccountJList accountJList, int userID) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            List<Account> accounts = accountDao.getAll();
            Object arr[] = accounts.toArray();
            accountJList.setListData(arr);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Account account, int userID) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            accountDao.delete(account);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrUpdateAccount(Account account, int userID, int operID) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            switch (operID) {
                case 0: {
                    accountDao.save(account);
                    break;
                }
                case 1: {
                    accountDao.update(account);
                    break;
                }
            }
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
