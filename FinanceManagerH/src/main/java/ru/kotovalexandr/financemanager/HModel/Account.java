package ru.kotovalexandr.financemanager.HModel;

import java.util.List;

/**
 * Created by admin on 12.07.2016.
 */
public class Account {
    private int id;
    private String number;
    private double amount;
    private int userId;
    private String description;
    private List<Transaction> transactionList;

    public Account(String number, int userId, String description, List<Transaction> transactionArrayList){
        this.number = number;
        this.id=-1;
        this.description = description;
        this.userId = userId;
        this.transactionList = transactionArrayList;
        for (Transaction transaction : transactionArrayList) {
            if(transaction.isCheckIn()){
                this.amount += transaction.getAmount();
            }else{
                this.amount -= transaction.getAmount();
            }

        }
    }

    public Account(int id, String number, int userId, String description, List<Transaction> transactionArrayList) {
        this(number,userId,description, transactionArrayList);
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
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
