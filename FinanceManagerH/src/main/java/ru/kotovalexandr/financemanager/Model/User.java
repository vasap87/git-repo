package ru.kotovalexandr.financemanager.Model;


import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 03.10.16.
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASS")
    private String pass;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    @Cascade(CascadeType.REPLICATE)
    private List<Account> accounts = new ArrayList<>();

    public User() {
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
