package ru.vasilenkoaleksandr.myprojects.BlackJack;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class NumberCard extends JCard implements Card {

    private Random random = new Random();
    private int rank;

    public NumberCard(String name) {
        super(name);
        this.rank = random.nextInt(5)+6;
    }


    @Override
    public int getRank() {
        return rank;
    }
}
