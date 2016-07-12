package ru.gb.AlexVasilenko.java3.lesson1.financeManager.server;

/**
 * Created by admin on 11.07.2016.
 */
public class Transaction {
    private int id;
    private boolean isCheckIn;
    private long dateAndTime;
    private double amount;
    private String desription;

    public Transaction(int id, boolean isCheckIn, long dateAndTime, double amount, String desription) {
        this.id = id;
        this.isCheckIn = isCheckIn;
        this.dateAndTime = dateAndTime;
        this.amount = amount;
        this.desription = desription;
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
