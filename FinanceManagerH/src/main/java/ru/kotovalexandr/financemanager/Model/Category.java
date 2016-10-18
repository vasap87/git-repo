package ru.kotovalexandr.financemanager.Model;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 */
@Entity
@Table(name = "CATEGORIES")
public class Category  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Transaction> transactionList = new ArrayList<>();

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        this.id = -1;
    }

    public Category(String name, int id) {
        this(name);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
