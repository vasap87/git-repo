package ru.gb.alexvasilenko.java3.lesson2.FinManager.Model;

/**
 * Created by Alex Vasilenko on 14.07.2016.
 */
public class Category extends Finance {
    private String name;
    private int id;

    public Category(String name, int id) {
        this.name = name;
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
}
