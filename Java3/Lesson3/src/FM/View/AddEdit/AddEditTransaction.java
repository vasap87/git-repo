package FM.View.AddEdit;

import FM.Controller.Account.AccountList;
import FM.Controller.Transaction.TransactionList;
import FM.Dao.DBHelper;
import FM.Dao.TransactionDao;
import FM.Model.Account;
import FM.Model.Category;
import FM.Model.Transaction;
import FM.View.Tools.CategoryJComponent;
import FM.View.Tools.MyDateTimeTicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;

/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class AddEditTransaction extends JDialog implements ActionListener {
    private Account account;
    private Transaction transaction;

    private JRadioButton isCheckin;
    private JFormattedTextField amountTF;
    private CategoryJComponent categoryJComponent;
    private MyDateTimeTicker datetimeTF;
    private JTextArea descrTA;
    private JButton cancelButton;
    private JButton okButton;
    private Category category;

    public AddEditTransaction(JFrame owner, String title, boolean modal, Account account) {
        super(owner, title, modal);
        this.account = account;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setBounds(200,200,350,250);
        Box mainBox = Box.createVerticalBox();

        Box firstRow = Box.createHorizontalBox();
        isCheckin = new JRadioButton("Пополнение", false);
        firstRow.add(isCheckin);
        mainBox.add(firstRow);

        Box secondRow = Box.createHorizontalBox();
        secondRow.add(new JLabel("Сумма:"));
        secondRow.add(Box.createHorizontalStrut(10));

        NumberFormat amountFormat = NumberFormat.getNumberInstance();
        amountFormat.setMaximumFractionDigits(2);
        amountFormat.setMinimumFractionDigits(2);
        amountFormat.setMinimumIntegerDigits(1);
        amountFormat.setMaximumIntegerDigits(15);
        amountTF = new JFormattedTextField(amountFormat);
        amountTF.setColumns(15);


        secondRow.add(amountTF);
        mainBox.add(secondRow);

        Box thirdRow = Box.createHorizontalBox();
        thirdRow.add(new JLabel("Дата: "), Box.LEFT_ALIGNMENT);

        datetimeTF = new MyDateTimeTicker();
        thirdRow.add(datetimeTF, Box.RIGHT_ALIGNMENT);
        mainBox.add(thirdRow);

        Box fourthRow = Box.createHorizontalBox();
        fourthRow.add(new JLabel("Категория: "));

        categoryJComponent = new CategoryJComponent(this);
        fourthRow.add(categoryJComponent);
        mainBox.add(fourthRow);
        descrTA = new JTextArea(3,20);
        descrTA.setLineWrap(true);
        descrTA.setWrapStyleWord(true);
        mainBox.add(descrTA);

        Box buttonBox = Box.createHorizontalBox();
        okButton = new JButton("Сохранить");
        okButton.setActionCommand("save");
        okButton.addActionListener(this);
        buttonBox.add(okButton, Box.RIGHT_ALIGNMENT);
        cancelButton = new JButton("Отмена");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);
        buttonBox.add(cancelButton, Box.RIGHT_ALIGNMENT);
        mainBox.add(buttonBox);
        setContentPane(mainBox);

    }

    public AddEditTransaction(JFrame owner, String title, boolean modal, Account account, Transaction transaction){
        this(owner, title, modal, account);
        this.transaction = transaction;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "amount": {
                break;
            }
            case "cancel": {
                this.dispose();
                break;
            }
            case "save": {
                if(category==null){
                    category = categoryJComponent.getCategory();
                }
                if (transaction == null){
                    transaction = new Transaction(account.getId(),isCheckin.isSelected(),
                            Double.parseDouble(amountTF.getValue().toString()), category,
                            datetimeTF.getDate().getTime(), descrTA.getText());
                }else {
                    transaction.setAmount(Double.parseDouble(amountTF.getText()));
                    transaction.setCategory(category);
                    transaction.setCheckIn(isCheckin.isSelected());
                    transaction.setDesription(descrTA.getText());
                }
                try {
                    Connection connection = DBHelper.getInstance().getConnection();
                    TransactionDao transactionDao = new TransactionDao(connection);
                    transactionDao.save(transaction);
                    DBHelper.getInstance().closeConnection();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                AccountList.getInstance().notifyObservers();
                TransactionList.getInstance().notifyObservers();
                this.dispose();
                break;
            }
//            case "category": {
//                category = (Category) categoryJComboBox.getSelectedItem();
//                break;
//            }
        }
    }


}
