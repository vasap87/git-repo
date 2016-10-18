package ru.kotovalexandr.financemanager.Controller.Services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.kotovalexandr.financemanager.Hibernate.DAO.CommonGenDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Hibernate.DAO.TransactionDaoImpl;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Category;
import ru.kotovalexandr.financemanager.Model.Transaction;
import ru.kotovalexandr.financemanager.Model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 07.10.16.
 */
public class TransactionDAOTest {
    User user;
    Account account;
    Category category;
    Transaction transaction;
    String accountNumber = "accountNumber123";
    String accountDescription = "accountDescription";
    String transactionDescr = "transactionDescription test";
    @Before
    public void setUp() throws Exception {
        user = SignInOnService.registrationService("testUser", "testPas");
        account = new Account(accountNumber, user, accountDescription);
        category = new Category("test category");
        transaction = new Transaction(account,true,new BigDecimal("1234567890.12"),category, System.currentTimeMillis(), transactionDescr);
        CategoryService.addOrUpdateCategory(category,0);
        AccountService.addOrUpdateAccount(account,0);
    }

    @After
    public void tearDown() throws Exception {
        CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
        commonGenDao.delete(transaction);
        commonGenDao.delete(account);
        commonGenDao.delete(category);
        commonGenDao.delete(user);
    }

    @Test
    public void saveTransactionTest() {
        CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
        commonGenDao.save(transaction);
        assertNotEquals(-1, transaction.getId());
    }

    @Test
    public void updateTransactionTest()  {
        CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
        commonGenDao.save(transaction);
        transaction.setDesription("new description");
        commonGenDao.update(transaction);
        TransactionDaoImpl transactionDao = DAOFabric.getTransactionDao();
        Transaction updTransaction = transactionDao.findById(transaction.getId());
        assertEquals("new description", updTransaction.getDesription());
    }

    @Test
    public void getAllTransactionByAccount()  {
        CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
        commonGenDao.save(transaction);
        TransactionDaoImpl transactionDao = DAOFabric.getTransactionDao();
        List<Transaction> transactions = transactionDao.getAllbyAccount(account);
        assertEquals(1,transactions.size());
    }

}