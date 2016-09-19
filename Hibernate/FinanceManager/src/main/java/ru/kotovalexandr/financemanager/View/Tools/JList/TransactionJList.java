package ru.kotovalexandr.financemanager.View.Tools.JList;

import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Controller.IObserver;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Dao.TransactionDao;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.View.AddEdit.AddEditTransaction;
import ru.kotovalexandr.financemanager.View.Tools.JList.Renders.ListTransactionRender;
import ru.kotovalexandr.financemanager.Model.Transaction;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 * This Class extends {@link JList}
 * its {@link Override} method remove of {@link JList}
 */
public class TransactionJList extends JList implements IObserver {

    private JFrame frame;
    List<Transaction> transactions;
    AccountJList accountJList;

    /**
     * Constructor to create Swing Object of this class
     * with {@link JList} behavior;
     * */
    public TransactionJList(JFrame frame, AccountJList accountJList) {
        super();
        this.frame = frame;
        this.accountJList = accountJList;
        setCellRenderer(new ListTransactionRender());
        JPopupMenu popupMenu = new JPopupMenu("Транзакция");
        JMenuItem add = new JMenuItem("Добавить");
        add.addActionListener(e -> new AddEditTransaction(frame, "Добавление транзакции", true,
                (Account) accountJList.getSelectedValue()).setVisible(true));
        JMenuItem edit = new JMenuItem("Редактировать");
        edit.addActionListener(e -> {
            Transaction transaction = null;
            if(!isSelectionEmpty()) {
                transaction = (Transaction) getSelectedValue();
            }else{
                setSelectedIndex(0);
                transaction = (Transaction) getSelectedValue() ;
            }
                new AddEditTransaction(frame, "Добавление транзакции", true,
                        (Account) accountJList.getSelectedValue(), transaction).setVisible(true);

        });
        JMenuItem delete = new JMenuItem("Удалить");
        delete.addActionListener(e -> remove(getSelectedIndex()));

        popupMenu.add(add);
        popupMenu.add(edit);
        popupMenu.add(delete);
        setComponentPopupMenu(popupMenu);


    }

    /**
     * This method check, if there ara no items selected in {@link JList},
     * we selected first, and then we ask user by using {@link JOptionPane},
     * if hi want delete this item? we delete it.
     * */
    @Override
    public void remove(int index) {
        if(isSelectionEmpty()){
            setSelectedIndex(0);
        }
        Transaction transaction = (Transaction) getSelectedValue();
        Date date = new Date(transaction.getDateAndTime());
        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        int answer = JOptionPane.showConfirmDialog(this, "Вы уверены что ходите удалить транзакцию на сумму "+ transaction.getAmount() +
                " от " + currentDate.format(date) + " ?", "Подтверждение удаление элемента",
                JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(answer == 0){
            try {
                Connection connection = DBHelper.getInstance().getConnection();
                TransactionDao transactionDao = new TransactionDao(connection);
                transactionDao.delete(transaction);
                handelEvent();
                AccountList.getInstance().notifyObservers();
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void handelEvent() {
        if(accountJList.isSelectionEmpty()){
            accountJList.setSelectedIndex(0);
        }
        Account account = (Account) accountJList.getSelectedValue();
        if(account!=null) {
            try {
                Connection connection = DBHelper.getInstance().getConnection();
                TransactionDao transactionDao = new TransactionDao(connection);
                transactions = transactionDao.getAllByAccout(account);
                Object arr[] = transactions.toArray();
                setListData(arr);
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
