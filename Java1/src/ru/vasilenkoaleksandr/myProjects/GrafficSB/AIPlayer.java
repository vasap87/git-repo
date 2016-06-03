package ru.vasilenkoaleksandr.myProjects.GrafficSB;


import java.util.Random;

/**
 * Created by admin on 29.05.2016.
 */
public class AIPlayer extends Player {
    private Random rand;

    @Override
    public void turn(ButtleField buttleField){
        rand = new Random();
        int x,y;
        do {
            x = rand.nextInt(ButtleField.SIZE);
            y = rand.nextInt(ButtleField.SIZE);
        }while(!buttleField.strike(x,y)||buttleField.isHit());
    }
}
