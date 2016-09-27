package ru.kotovalexandr.financemanager.Model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by admin on 12.07.2016.
 */
public class Account {
    private int id;
    private String number;
    private BigDecimal amount = new BigDecimal(0);
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
                amount = amount.add(transaction.getAmount());
            }else{
                amount = amount.subtract(transaction.getAmount());
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

    public BigDecimal getAmount() {
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
