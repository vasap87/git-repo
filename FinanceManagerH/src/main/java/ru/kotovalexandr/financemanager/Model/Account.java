package ru.kotovalexandr.financemanager.Model;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 12.07.2016.
 */
@Entity
@Table(name = "ACCOUNTS")
public class Account  implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "AMOUNT")
    private BigDecimal amount = new BigDecimal(0);

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "DESCR")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Transaction> transactionList = new ArrayList<>();

    public Account() {
    }

    public Account(String number, User user, String description){
        this.number = number;
        this.id=-1;
        this.description = description;
        this.user = user;
        user.addAccount(this);

    }

    public Account(int id, String number, User user, String description) {
        this(number,user,description);
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
        return user.getId();
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

    public void updateAmmount() {
        for (Transaction transaction : transactionList) {
            if(transaction.isCheckIn()){
                amount = amount.add(transaction.getAmount());
            }else{
                amount = amount.subtract(transaction.getAmount());
            }
        }
    }
}
