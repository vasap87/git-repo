package ru.vasilenkoaleksandr.myprojects.BlackJack;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class SuitCard extends JCard implements Card {

    public SuitCard(String name) {
        super(name);
    }

    @Override
    public int getRank() {
        return 10;
    }
}
