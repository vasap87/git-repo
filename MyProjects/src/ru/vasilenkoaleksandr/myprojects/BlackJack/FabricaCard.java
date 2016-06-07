package ru.vasilenkoaleksandr.myprojects.BlackJack;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 09.06.2016.
 */
public class FabricaCard {
    private static FabricaCard ourInstance = new FabricaCard();
    private Random rand = new Random();

    public static FabricaCard getInstance() {
        return ourInstance;
    }

    private FabricaCard() {
        String[] cards = {"6", "7", "8", "9", "10", "J", "D", "K", "A"};

    }
}
