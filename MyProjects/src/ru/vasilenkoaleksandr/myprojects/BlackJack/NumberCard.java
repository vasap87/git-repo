package ru.vasilenkoaleksandr.myprojects.BlackJack;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class NumberCard implements Card {

    private Random random = new Random();

    @Override
    public int getRank() {
        return random.nextInt(5)+6;
    }
}
