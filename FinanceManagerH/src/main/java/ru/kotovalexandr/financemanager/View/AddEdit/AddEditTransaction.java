package ru.kotovalexandr.financemanager.View.AddEdit;



import ru.kotovalexandr.financemanager.Controller.Account.AccountList;
import ru.kotovalexandr.financemanager.Controller.Services.TransactionService;
import ru.kotovalexandr.financemanager.Controller.Transaction.TransactionList;
import ru.kotovalexandr.financemanager.Model.*;
import ru.kotovalexandr.financemanager.View.Tools.DatePicker.MyDateTimeTicker;
import ru.kotovalexandr.financemanager.View.Tools.JCategory.CategoryJComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

/**
 * Created by vasilenko.aleksandr on 27.07.2016.
 */
public class AddEditTransaction extends JDialog implements ActionListener {
    private Account account;
    private Transaction transaction;
    private int operID;

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
        operID = 0;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        setBounds(200,200,350,300);
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
        operID = 1;
        isCheckin.setSelected(transaction.isCheckIn());
        amountTF.setValue(transaction.getAmount().setScale(2,BigDecimal.ROUND_HALF_EVEN));
        datetimeTF.setDate(new Date(transaction.getDateAndTime()));
        categoryJComponent.setCategory(transaction.getCategory());
        descrTA.setText(transaction.getDesription());
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
                    transaction = new Transaction(account,isCheckin.isSelected(),
                            new BigDecimal(amountTF.getValue().toString()), category,
                            datetimeTF.getDate().getTime(), descrTA.getText());
                }else {
                    transaction.setAmount(new BigDecimal(amountTF.getValue().toString()).setScale(2,BigDecimal.ROUND_HALF_EVEN));
                    transaction.setCategory(category);
                    transaction.setCheckIn(isCheckin.isSelected());
                    transaction.setDesription(descrTA.getText());
                }
                TransactionService.addOrUpdateTransaction(transaction, operID);
                AccountList.getInstance().notifyObservers();
                TransactionList.getInstance().notifyObservers();
                this.dispose();
                break;
            }
        }
    }


}
