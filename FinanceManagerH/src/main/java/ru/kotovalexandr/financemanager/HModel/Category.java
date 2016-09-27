package ru.kotovalexandr.financemanager.HModel;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 */

@Entity
public class Category {


    @Id
    @GeneratedValue
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String name;

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
