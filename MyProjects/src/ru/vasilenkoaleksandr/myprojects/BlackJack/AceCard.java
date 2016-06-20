package ru.vasilenkoaleksandr.myprojects.BlackJack;

import javax.swing.*;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class AceCard extends JCard implements Card {


    public AceCard(String name) {
        super(name);
    }

    @Override
    public int getRank() {
        return 11;
    }
}
