package ru.kotovalexandr.financemanager.Controller.Services;



import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Controller.Transaction.TransactionList;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Dao.TransactionDao;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Transaction;
import ru.kotovalexandr.financemanager.View.Tools.JList.AccountJList;
import ru.kotovalexandr.financemanager.View.Tools.JList.TransactionJList;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 24.08.2016.
 */
public final class TransactionService {

    private TransactionService() {
    }

    public static void updateList(TransactionJList transactionJList, AccountJList accountJList){
        if (accountJList.isSelectionEmpty()) {
            accountJList.setSelectedIndex(0);
        }
        Account account = (Account) accountJList.getSelectedValue();
        if (account != null) {
            try {
                Connection connection = DBHelper.getInstance().getConnection();
                TransactionDao transactionDao = new TransactionDao(connection);
                List<Transaction> transactions = transactionDao.getAllByAccout(account);
                Object arr[] = transactions.toArray();
                transactionJList.setListData(arr);
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void remove(Transaction transaction) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            TransactionDao transactionDao = new TransactionDao(connection);
            transactionDao.delete(transaction);
            TransactionList.getInstance().notifyObservers();
            AccountList.getInstance().notifyObservers();
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addOrUpdateTransaction(Transaction transaction, int operID) {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            TransactionDao transactionDao = new TransactionDao(connection);
            switch (operID){
                case 0: {
                    transactionDao.save(transaction);
                    break;
                }
                case 1: {
                    transactionDao.update(transaction);
                    break;
                }
            }
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
