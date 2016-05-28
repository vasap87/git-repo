package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class AIPlayer extends Player {
    private Random rand = new Random();
    static int tempCount=1;
    private int aICount;

    public AIPlayer(){
        super();
        aICount = tempCount++;
    }

    @Override
    public void turn(GameField gameField) {
        System.out.println("Ход компьютера " + aICount);
        int x,y;
        do {
            x = rand.nextInt(GameField.SIZE);
            y = rand.nextInt(GameField.SIZE);
        }while (!gameField.strike(x,y)||gameField.isHit());
    }


}
