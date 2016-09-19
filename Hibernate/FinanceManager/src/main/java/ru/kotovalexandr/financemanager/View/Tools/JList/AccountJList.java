package ru.kotovalexandr.financemanager.View.Tools.JList;

import ru.kotovalexandr.financemanager.Controller.IObserver;
import ru.kotovalexandr.financemanager.Dao.AccountDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.View.Tools.JList.Renders.ListAccountRender;
import ru.kotovalexandr.financemanager.View.AddEdit.AddEditAccount;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 * This Class extends {@link JList} and implement interface {@link IObserver} to
 * can take changes in model.
 * its {@link Override} method remove of {@link JList} and
 * method handelEvent of interface {@link IObserver}
 */
public class AccountJList extends JList implements IObserver {

    private int userID;
    private JFrame frame;
    List<Account> accounts;
    private int index;

    /**
     * Constructor to create Swing Object of this class
     * with {@link JList} behavior;
     */
    public AccountJList(JFrame frame, int userID) {
        super();
        this.userID = userID;
        this.frame = frame;
        setCellRenderer(new ListAccountRender());
        JPopupMenu popupMenu = new JPopupMenu("Счёт");
        JMenuItem add = new JMenuItem("Добавить");
        add.addActionListener(e -> new AddEditAccount(frame, "Добавление счёта", true, userID).setVisible(true));
        JMenuItem edit = new JMenuItem("Редактировать");
        edit.addActionListener(e -> {
            Account account = getSelectedAccount();
            new AddEditAccount(frame, "Редактирование счёта", true, userID, account).setVisible(true);

        });
        JMenuItem delete = new JMenuItem("Удалить");
        delete.addActionListener(e -> remove(getSelectedIndex()));

        popupMenu.add(add);
        popupMenu.add(edit);
        popupMenu.add(delete);
        setComponentPopupMenu(popupMenu);


    }


    @Override
    public void handelEvent() {
        try {
            Connection connection = DBHelper.getInstance().getConnection();
            AccountDao accountDao = new AccountDao(connection, userID);
            accounts = accountDao.getAll();
            Object arr[] = accounts.toArray();
            setListData(arr);
            setSelectedIndex(index);
            DBHelper.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method check, if there ara no items selected in {@link JList},
     * we selected first, and then we ask user by using {@link JOptionPane},
     * if hi want delete this item? we delete it.
     */
    @Override
    public void remove(int index) {
        Account account = getSelectedAccount();
        int answer = JOptionPane.showConfirmDialog(this, "Вы уверены что ходите удалить счёт №" + account.getNumber(),
                "Подтверждение удаление эллемента", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (answer == 0) {
            try {
                Connection connection = DBHelper.getInstance().getConnection();
                AccountDao accountDao = new AccountDao(connection, userID);
                accountDao.delete(account);
                handelEvent();
                DBHelper.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Account getSelectedAccount() {
        if (isSelectionEmpty()) {
            setSelectedIndex(0);
        }
        return (Account) getSelectedValue();
    }

    public void setIndex() {
        index = getSelectedIndex();
    }
}
