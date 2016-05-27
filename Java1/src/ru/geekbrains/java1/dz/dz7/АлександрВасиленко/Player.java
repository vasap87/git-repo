package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public abstract class Player {
    private GameField gameField;


    public Player(){
        gameField = new GameField();
    }

    public GameField getGameField() {
        return gameField;
    }

    public abstract void turn(GameField gameField);
}
