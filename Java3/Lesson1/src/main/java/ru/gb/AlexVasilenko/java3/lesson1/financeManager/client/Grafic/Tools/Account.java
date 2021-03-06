package ru.gb.AlexVasilenko.java3.lesson1.financeManager.client.Grafic.Tools;

import ru.gb.AlexVasilenko.java3.lesson1.financeManager.server.Tools.Transaction;

import java.util.List;

/**
 * Created by admin on 12.07.2016.
 */
public class Account {
    private String number;
    private double amount;
    private String user;
    private String description;
    private List<Transaction> transactionList;

    public Account(String number, String user, String description, List<Transaction> transactionArrayList) {
        this.number = number;
//        this.amount = amount;
        this.description = description;
        this.user = user;
        this.transactionList = transactionArrayList;
        for (Transaction transaction: transactionArrayList){
            this.amount+=transaction.getAmount();
        }
    }

    public String getNumber() {
        return number;
    }

    public double getAmount() {
        return amount;
    }

    public String getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
