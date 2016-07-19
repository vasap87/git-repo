package ru.gb.alexvasilenko.java3.lesson2.fix;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public class Boy extends Man {
    private static final String SEX = "male";
    public Boy(int uid, String name, int age) {
        super(uid, name, age, SEX);
    }

    public boolean think() {
        return true;
    }
}
