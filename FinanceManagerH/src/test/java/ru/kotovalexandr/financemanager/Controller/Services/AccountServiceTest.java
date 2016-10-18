package ru.kotovalexandr.financemanager.Controller.Services;


import ru.kotovalexandr.financemanager.Hibernate.DAO.AccountDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Model.Account;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.kotovalexandr.financemanager.Model.User;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 25.08.2016.
 */
public class AccountServiceTest {
    Account account;
    User user;
    String accountNumber = "accountNumber123";
    String accountDescription = "accountDescription";

    @Before
    public void setUp() throws Exception {
        user = SignInOnService.registrationService("testUser", "testPas");
        account = new Account(accountNumber, user, accountDescription);
    }

    @Test
    public void addAccountTest() throws Exception {
        AccountService.addOrUpdateAccount(account, 0);
        //проверка добавления счёта
        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);

        Account account = accounts.get(0);
        assertEquals(this.account.getId(), account.getId());
    }

    @Test
    public void updateAccountTest() throws Exception {
        AccountService.addOrUpdateAccount(account, 0);
        account.setDescription("new description");
        AccountService.addOrUpdateAccount(account, 1);
        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);
        Account account = accounts.get(0);
        assertEquals(this.account.getDescription(), account.getDescription());
    }

    @Test
    public void removeAccountTest() throws Exception {
        Account removableAccount = new Account(accountNumber + "remove", user, accountDescription);
        AccountService.addOrUpdateAccount(removableAccount, 0);

        AccountService.remove(removableAccount);

        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);

        assertEquals(accounts.size(), 0);
        AccountService.addOrUpdateAccount(account, 0);
    }

    @After
    public void tearDown() throws Exception {
        AccountService.remove(account);
        SignInOnService.deleteUserService(user);
    }
}