package ru.kotovalexandr.financemanager.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by admin on 11.07.2016.
 */
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "ISCHECKIN")
    private boolean isCheckIn;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @Column(name = "DATETIME")
    private long dateAndTime;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "DESCR")
    private String desription;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Transaction() {
    }

    public Transaction(int id, boolean isCheckIn, Account account, long dateAndTime, BigDecimal amount, String desription, Category category) {
        this(account, isCheckIn, amount, category, dateAndTime, desription);
        this.id = id;
    }

    public Transaction(Account account, boolean isCheckIn, BigDecimal amount, Category category, long dateAndTime, String desription) {
        this.id = -1;
        this.account = account;
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
        return account.getId();
    }


    public long getDateAndTime() {
        return dateAndTime;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"isCheckIn\":" + isCheckIn +
                ",\"dateAndTime\":" + dateAndTime +
                ",\"amount\":" + amount.toString() +
                ",\"desription\":" + desription +
                '}';
    }
}
