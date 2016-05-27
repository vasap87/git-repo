package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class AIPlayer extends Player {
    private Random rand = new Random();

    public AIPlayer(){
        super();
    }

    @Override
    public void turn(GameField gameField) {
        System.out.println("Ход компьютера");
        int x = rand.nextInt(GameField.SIZE);
        int y = rand.nextInt(GameField.SIZE);
        gameField.strike(x,y);
    }
}
