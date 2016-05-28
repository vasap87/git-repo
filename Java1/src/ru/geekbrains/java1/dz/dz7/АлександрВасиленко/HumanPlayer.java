package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import java.util.Scanner;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class HumanPlayer extends Player {
    private Scanner sc = new Scanner(System.in);

    public HumanPlayer() {
        super();
    }

    @Override
    public void turn(GameField gameField) {
        System.out.println("Ходит игрок с именем "+getName());
        int x,y;
        do {
            x = sc.nextInt()-1;
            y = sc.nextInt()-1;
        }while (!gameField.strike(x,y)||gameField.isHit());
    }
}
