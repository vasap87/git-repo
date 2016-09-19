package ru.kotovalexandr.financemanager.View.AddEdit;

import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Dao.AccountDao;
import ru.kotovalexandr.financemanager.Dao.DBHelper;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Transaction;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 */
public class AddEditAccount extends JDialog implements ActionListener {

    private int userID;
    private int operID;

    private Color color;

    private Box fieldsPanel;
    private JTextField numberTF;
    private JTextArea descrTA;
    private JPanel buttonPanel;
    private JButton cancelButton;
    private JButton okButton;
    private Account account;

    public AddEditAccount(Frame owner, String title, boolean modal, int userID) {
        super(owner, title, modal);
        this.userID = userID;
        operID = 0;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(200, 200);
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        fieldsPanel = Box.createVerticalBox();
        numberTF = new JTextField(20);
        numberTF.setBorder(new TitledBorder("Номер счёта"));
        fieldsPanel.add(numberTF);
        fieldsPanel.add(Box.createHorizontalStrut(5));
        descrTA = new JTextArea(3, 20);
        descrTA.setLineWrap(true);
        descrTA.setWrapStyleWord(true);
        descrTA.setBorder(new TitledBorder("Описание счёта"));
        fieldsPanel.add(descrTA);
        panel.add(fieldsPanel, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cancelButton = new JButton("Отмена");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
        okButton = new JButton("Сохранить");
        okButton.setActionCommand("save");
        okButton.addActionListener(this);
        buttonPanel.add(okButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);

        color = numberTF.getBackground();
    }

    public AddEditAccount(Frame owner, String title, boolean modal, int userID, Account account) {
        this(owner, title, modal, userID);
        this.account = account;
        operID = 1;
        numberTF.setText(account.getNumber());
        descrTA.setText(account.getDescription());

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "cancel": {
                this.dispose();
                break;
            }
            case "save": {
                if (checkFields()) {
                    if (account != null) {
                        account.setNumber(numberTF.getText());
                        account.setDescription(descrTA.getText());
                    }
                    try {
                        Connection connection = DBHelper.getInstance().getConnection();
                        AccountDao accountDao = new AccountDao(connection, userID);
                        switch (operID) {
                            case 0: {
                                accountDao.save(new Account(numberTF.getText(), userID, descrTA.getText(), new ArrayList<Transaction>()));
                                break;
                            }
                            case 1: {
                                accountDao.update(account);
                                break;
                            }
                        }
                        DBHelper.getInstance().closeConnection();
                        AccountList.getInstance().notifyObservers();
                        this.dispose();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    private boolean checkFields() {
        int count = 0;
        if (checkComponent(numberTF)) {
            count++;
        }
        if (checkComponent(descrTA)) {
            count++;
        }
        if (count != 0) return false;
        return true;
    }

    private boolean checkComponent(JTextComponent textComponent) {
        if (textComponent.getText().trim().isEmpty()) {
            backLightComponent(textComponent);
            return true;
        } else {
            textComponent.setBackground(color);
        }
        return false;
    }

    private void backLightComponent(JComponent component) {
        component.setBackground(Color.ORANGE);
    }
}
