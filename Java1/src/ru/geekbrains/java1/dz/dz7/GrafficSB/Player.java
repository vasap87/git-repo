package ru.geekbrains.java1.dz.dz7.GrafficSB;


/**
 * Created by admin on 29.05.2016.
 */
public abstract class Player {
    private ButtleField buttleField;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Player(){
        buttleField = new ButtleField();
    }

    public ButtleField getButtleField() {
        return buttleField;
    }

    public abstract void turn(ButtleField buttleField);
}
