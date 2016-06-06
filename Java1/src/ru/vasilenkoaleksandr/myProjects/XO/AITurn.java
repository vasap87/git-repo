package ru.vasilenkoaleksandr.myProjects.XO;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 03.06.2016.
 * Класс для хода компьютера
 */
public class AITurn {

    private Random rand = new Random();
    private static AITurn instance;


    //метод доступа к единственному объекту AITurn
    public static AITurn getInstance() {
        if (instance == null) {
            instance = new AITurn();
        }
        return instance;
    }

    //закрываем конструктор
    private AITurn() {

    }


    // Ход компьютера
    public void aiTurn() {
        int x, y;
        //получить координаты хода
        if (FindTurn.getInstance().isBlock()) {
            x = FindTurn.getInstance().getX();
            y = FindTurn.getInstance().getY();
            //походить
            GameField.getInstance().turn(x, y, GameField.AI);
        }
        //если нечего блокировать - ходим рандомно
        else {
            do{
                x = rand.nextInt(GameField.SIZE);
                y = rand.nextInt(GameField.SIZE);
            }while (!GameField.getInstance().turn(x,y,GameField.AI));

        }



    }

}
