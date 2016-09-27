package ru.kotovalexandr.financemanager.Controller.Services;

import ru.kotovalexandr.financemanager.Dao.AccountDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Dao.SignInOn;
import ru.kotovalexandr.financemanager.Model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 25.08.2016.
 */
public class AccountServiceTest {
    Account account;
    int userID;
    String accountNumber = "accountNumber123";
    String accountDescription = "accountDescription";

    @Before
    public void setUp() throws Exception {
        userID = SignInOn.getInstance().registration("testUser", "testPas");
        account = new Account(accountNumber, userID, accountDescription, new ArrayList<>());
    }

    @Test
    public void addAccountTest() throws Exception {
        AccountService.getInstance().addOrUpdateAccount(account, userID, 0);
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            accounts = accountDao.getAll();
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Account account = accounts.get(0);
        assertEquals(this.account.getId(), account.getId());
    }

    @Test
    public void updateAccountTest() throws Exception {
        AccountService.getInstance().addOrUpdateAccount(account, userID, 0);
        account.setDescription("new description");
        AccountService.getInstance().addOrUpdateAccount(account, userID, 1);
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            accounts = accountDao.getAll();
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Account account = accounts.get(0);
        assertEquals(this.account.getDescription(), account.getDescription());
    }

    @Test
    public void removeAccountTest() throws Exception {
        Account removableAccount = new Account(accountNumber+"remove", userID, accountDescription, new ArrayList<>());

        AccountService.getInstance().addOrUpdateAccount(removableAccount, userID, 0);
        AccountService.getInstance().remove(removableAccount, userID);
        List<Account> accounts = new ArrayList<>();
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            accounts = accountDao.getAll();
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(accounts.size(),0);
        AccountService.getInstance().addOrUpdateAccount(account, userID, 0);
    }

    @After
    public void tearDown() throws Exception {
        AccountService.getInstance().remove(account, userID);
        SignInOn.getInstance().removeUser(userID);
    }
}