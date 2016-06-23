package ru.vasilenkoaleksandr.myprojects.ListCase;

import java.text.DateFormatSymbols;

/**
 * Created by vasilenko.aleksandr on 22.06.2016.
 */
public class SimpleCase {
    private String date;
    private String name;
    private String description;

    public SimpleCase(String date, String name, String description) {
        this.date = date;
        this.name = name;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
