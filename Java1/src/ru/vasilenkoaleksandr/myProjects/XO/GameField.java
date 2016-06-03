package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 * SingleTon pattern: this instance is uniq.
 */
public class GameField {

    public static final char DEFAULT = ' ';
    /**
     * Поле игры
     */
    private char[][] field;

    /**
     * Константа для задания размера игровому полю
     */
    public static final int SIZE = 3;

    /**
     * Константа описывающая длинну выигрышной комбинации
     */
    public static final int WIN = 3;

    /**
     * Константа для отображения знака игрока
     */
    public static final char PLAYER = 'X';

    /**
     * Константа для отображения знака компьютера
     */
    public static final char AI = 'O';



    /**
     * Переменная для доступа к единственному объекту класса
     */
    private static GameField instance;

    public static GameField getInstance() {
        if (instance == null) {
            instance = new GameField();
        }
        return instance;
    }

    private GameField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = DEFAULT;
            }
        }
    }

    public boolean turn(int x, int y, char ch) {
        if (field[x][y] == DEFAULT) {
            field[x][y] = ch;
            return true;
        }
        return false;
    }



    /**
     * Проверка на выигрыш
     */
    public boolean isAnybodyWin() {
        if (AITurn.getInstance().isGorizontal(WIN)) {
            return true;
        } else if (AITurn.getInstance().isVertical(WIN)) {
            return true;
        } else if (AITurn.getInstance().isDiagonal(WIN)) {
            return true;
        } else if (AITurn.getInstance().isBackWardDiagonal(WIN)) {
            return true;
        } else
            return false;
    }

    /**
     * Проверка на ничью
     */
    public boolean isNobodyWin() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }


    public char[][] getField() {
        return field;
    }



}
