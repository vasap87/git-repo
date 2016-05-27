package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class Main {
    public static void main(String[] args) {
        Player human = new HumanPlayer();
        Player AI = new AIPlayer();
        human.getGameField().printField(true);
        while(true){
            human.turn(AI.getGameField());
            AI.getGameField().printField(true);
            if(AI.getGameField().isDefeated()) break;
            AI.turn(human.getGameField());
            human.getGameField().printField(true);
            if(human.getGameField().isDefeated()) break;
        }
    }
}
