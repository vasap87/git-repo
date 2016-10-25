package ru.kotovalexandr.financemanager.Controller.Services;


import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Controller.Transaction.TransactionList;
import ru.kotovalexandr.financemanager.Hibernate.DAO.CommonGenDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Hibernate.DAO.TransactionDaoImpl;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Transaction;
import ru.kotovalexandr.financemanager.View.Tools.JList.AccountJList;
import ru.kotovalexandr.financemanager.View.Tools.JList.TransactionJList;

import java.util.List;

/**
 * Created by vasilenko.aleksandr on 24.08.2016.
 */
public final class TransactionService {

    private TransactionService() {
    }

    public static void updateList(TransactionJList transactionJList, AccountJList accountJList) {
        if (accountJList.isSelectionEmpty()) {
            accountJList.setSelectedIndex(0);
        }
        Account account = (Account) accountJList.getSelectedValue();
        if (account != null) {
            TransactionDaoImpl transactionDao = DAOFabric.getTransactionDao();
            List<Transaction> transactions = transactionDao.getAllbyAccount(account);
            Object arr[] = transactions.toArray();
            transactionJList.setListData(arr);
        } else {
            Object arr[] = new Object[0];
            transactionJList.setListData(arr);
        }
    }

    public static void remove(Transaction transaction) {
        CommonGenDaoImpl transactionDao = DAOFabric.getCommonDao();
        transactionDao.delete(transaction);
        TransactionList.getInstance().notifyObservers();
        AccountList.getInstance().notifyObservers();
    }

    public static void addOrUpdateTransaction(Transaction transaction, int operID) {
        CommonGenDaoImpl transactionDao = DAOFabric.getCommonDao();
        switch (operID) {
            case 0: {
                transactionDao.save(transaction);
                break;
            }
            case 1: {
                transactionDao.update(transaction);
                break;
            }
        }

    }
}
