package ru.gb.alexvasilenko.java3.lesson2.FinManager.Model;

import java.util.List;

/**
 * Created by admin on 12.07.2016.
 */
public class Account extends Finance{
    private int id;
    private String number;
    private double amount;
    private int userId;
    private String description;
    private List<Transaction> transactionList;

    public Account(String number, int userId, String description, List<Transaction> transactionArrayList) {
        this.number = number;
//        this.amount = amount;
        this.description = description;
        this.userId = userId;
        this.transactionList = transactionArrayList;
        for (Transaction transaction : transactionArrayList) {
            this.amount += transaction.getAmount();
        }
    }

    public String getNumber() {
        return number;
    }

    public double getAmount() {
        return amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
