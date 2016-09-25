package ru.kotovalexandr.financemanager.Model;

/**
 * Created by admin on 11.07.2016.
 */
public class Transaction {
    private int id;
    private boolean isCheckIn;
    private int account_id;
    private long dateAndTime;
    private double amount;
    private String desription;
    private Category category;

    public Transaction(int id, boolean isCheckIn, int account_id, long dateAndTime, double amount, String desription, Category category) {
        this(account_id, isCheckIn, amount, category, dateAndTime, desription);
        this.id = id;
    }

    public Transaction(int account_id, boolean isCheckIn, double amount, Category category, long dateAndTime, String desription) {
        this.id = -1;
        this.account_id = account_id;
        this.amount = amount;
        this.category = category;
        this.dateAndTime = dateAndTime;
        this.desription = desription;
        this.isCheckIn = isCheckIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheckIn() {
        return isCheckIn;
    }

    public void setCheckIn(boolean checkIn) {
        isCheckIn = checkIn;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDesription() {
        return desription;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"isCheckIn\":" + isCheckIn +
                ",\"dateAndTime\":" + dateAndTime +
                ",\"amount\":" + amount +
                ",\"desription\":" + desription +
                '}';
    }
}
