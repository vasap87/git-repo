package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server.Tools;

/**
 * Created by admin on 11.07.2016.
 */
public class Transaction {
    private int id;
    private String account_num;
    private boolean isCheckIn;
    private long dateAndTime;
    private double amount;
    private String desription;

    public Transaction(int id, String account_num, boolean isCheckIn, long dateAndTime, double amount, String desription) {
        this.id = id;
        this.account_num = account_num;
        this.isCheckIn = isCheckIn;
        this.dateAndTime = dateAndTime;
        this.amount = amount;
        this.desription = desription;
    }

    public int getId() {
        return id;
    }

    public String getAccount_num() {
        return account_num;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public long getDateAndTime() {
        return dateAndTime;
    }

    public double getAmount() {
        return amount;
    }

    public String getDesription() {
        return desription;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"isCheckIn\":" + isCheckIn +
                ",\"dateAndTime\":" + dateAndTime +
                ",\"amount\":" + amount +
                ",\"desription\":" + desription  +
                '}';
    }
}
