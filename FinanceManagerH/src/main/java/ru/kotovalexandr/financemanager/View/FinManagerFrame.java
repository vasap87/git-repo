package ru.kotovalexandr.financemanager.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Controller.Account.TotalAmount;
import ru.kotovalexandr.financemanager.Controller.Transaction.TransactionList;
import ru.kotovalexandr.financemanager.View.AddEdit.AddEditAccount;
import ru.kotovalexandr.financemanager.View.AddEdit.AddEditTransaction;
import ru.kotovalexandr.financemanager.View.Tools.JList.AccountJList;
import ru.kotovalexandr.financemanager.View.Tools.JList.TransactionJList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;

/**
 * Created by vasilenko.aleksandr on 11.07.2016.
 */
public class FinManagerFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;

    private static Logger logger = LoggerFactory.getLogger(FinManagerFrame.class);

    private String login;
    private int loginID;

    private JLabel totalJLabel;

    private AccountJList accountJList;
    private TransactionJList transactionJList;

    public FinManagerFrame(String login, int loginID) {
        this.login = login;
        this.loginID = loginID;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setBounds(200, 200, getMinimumSize().width, getMinimumSize().height);

        setResizable(true);
        setTitle("Финансы пользователя: " + this.login);
        setLayout(new BorderLayout(5, 5));

        addMenu();
        addAccountPanel();
        addTransactionPanel();

        addingData();
        addNorthPanel();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                super.windowActivated(e);
                updateNorthPanel();
            }
        });
        setVisible(true);
    }

    private void updateNorthPanel() {
        BigDecimal total = TotalAmount.getInstance().getTotalAmount(loginID);
        totalJLabel.setText("На текущий момент на ваших счетах "+ total.toPlainString() + " рублей");
    }

    private void addNorthPanel() {
        totalJLabel = new JLabel();
        updateNorthPanel();
        add(totalJLabel, BorderLayout.NORTH);
    }


    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Финансы");
        JMenuItem changeLoginMenu = new JMenuItem("Сменить пользователя");
        changeLoginMenu.addActionListener(e -> {
            new LoginJFrame();
            this.dispose();
        });
        menu.add(changeLoginMenu);

        JMenuItem registerUserMenu = new JMenuItem("Зарегистрировать нового \n пользователя");
        registerUserMenu.addActionListener(e -> {
            new RegistrationForm();
            this.dispose();
        });
        menu.add(registerUserMenu);

        JMenuItem exitMenu = new JMenuItem("Выход");
        exitMenu.addActionListener(e -> System.exit(-1));
        menu.add(new JSeparator());
        menu.add(exitMenu);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    private void addingData() {
        AccountList.getInstance().notifyObservers();
        TransactionList.getInstance().notifyObservers();
    }


    //добавление панели со счетами пользователя
    private void addAccountPanel() {
        JPanel accountPanel = new JPanel(new BorderLayout());
        //кнопки
        JPanel buttonAccoutPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addAccountButton = new JButton("+");
        addAccountButton.addActionListener(e -> new AddEditAccount(this, "Добавление счёта", true, loginID).setVisible(true));
        buttonAccoutPanel.add(addAccountButton);
        JButton removeAccountButton = new JButton("-");
        removeAccountButton.addActionListener(e -> {
            if (accountJList.isSelectionEmpty()) {
                accountJList.setSelectedIndex(0);
            }
            accountJList.remove(accountJList.getSelectedIndex());
        });
        buttonAccoutPanel.add(removeAccountButton);
        accountPanel.add(buttonAccoutPanel, BorderLayout.NORTH);
        //листбокс со счетами
        accountJList = new AccountJList(this, loginID);
        accountJList.addListSelectionListener(e -> {
            accountJList.setIndex();
            TransactionList.getInstance().notifyObservers();
        });
        AccountList.getInstance().addObserver(accountJList);
        JScrollPane jScrollPane = new JScrollPane(accountJList);
        accountPanel.add(jScrollPane, BorderLayout.CENTER);

        //добавление панели на форму
        accountPanel.setPreferredSize(new Dimension(WIDTH / 3, HEIGHT));
        add(accountPanel, BorderLayout.WEST);

    }

    private void addTransactionPanel() {
        JPanel transactionPanel = new JPanel(new BorderLayout());
        //кнопки
        JPanel buttonTransactionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addTransactionButton = new JButton("+");
        addTransactionButton.addActionListener(e -> new AddEditTransaction(this, "Добавление транзакции", true, accountJList.getSelectedAccount()).setVisible(true));
        buttonTransactionPanel.add(addTransactionButton);
        JButton removeTransactionButton = new JButton("-");
        removeTransactionButton.addActionListener(e -> {
            if(transactionJList.isSelectionEmpty()){
                transactionJList.setSelectedIndex(0);
            }
            transactionJList.remove(transactionJList.getSelectedIndex());
        });
        buttonTransactionPanel.add(removeTransactionButton);
        transactionPanel.add(buttonTransactionPanel, BorderLayout.NORTH);
        //листбокс со счетами
        transactionJList = new TransactionJList(this, accountJList);
        TransactionList.getInstance().addObserver(transactionJList);
        JScrollPane jScrollPane = new JScrollPane(transactionJList);
        transactionPanel.add(jScrollPane, BorderLayout.CENTER);

        //добавление панели на форму
        add(transactionPanel, BorderLayout.CENTER);

    }

}
