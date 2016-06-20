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

    public Card getRandomCard () {
        String[] cards = {"6", "7", "8", "9", "10", "J", "D", "K", "A"};
        Card card = null;
        int index = rand.nextInt(8);
        if(cards[index].equals("6")||cards[index].equals("7")||cards[index].equals("8")||cards[index].equals("9")||cards[index].equals("10")){
            card = new NumberCard(cards[index]);
        }else if (cards[index].equals("J")||cards[index].equals("D")||cards[index].equals("K")){
            card = new SuitCard(cards[index]);
        }else if (cards[index].equals("A")){
            card = new AceCard(cards[index]);
        }

        return card;
    }
}
