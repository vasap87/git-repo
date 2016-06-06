package ru.vasilenkoaleksandr.myProjects.XO;

import java.util.Arrays;

/**
 * Created by vasilenko.aleksandr on 06.06.2016.
 */
public class FindWin {
    private WinCoordinates[] winArray;
    private char[] playerWin, aiWin;
    private char[][] field = GameField.getInstance().getField();
    private static FindWin instance;

    //мето доступа к единственному объекту класса
    public static FindWin getInstance() {
        if (instance == null) {
            instance = new FindWin();
        }
        return instance;
    }

    //закрытый конструктор единственного объекта класса
    private FindWin() {

        //создаём выигрышный массив для игрока
        playerWin = new char[GameField.SIZE];
        Arrays.fill(playerWin, GameField.PLAYER);
        //создаём выигрышный массив для компа
        aiWin = new char[GameField.SIZE];
        Arrays.fill(aiWin, GameField.PLAYER);

    }

    public boolean isAnyBodyWin() {
        //смотрим по горизонтали
        for (int i = 0; i < field.length; i++) {
            if (Arrays.equals(field[i], playerWin) || Arrays.equals(field[i], aiWin)) {
                for (int j = 0; j < field.length; j++) {
                    winArray = new WinCoordinates[GameField.SIZE];
                    winArray[j] = new WinCoordinates(i, j);
                    return true;
                }
            }
        }

        //смотрим по вертикали
        for (int i = 0; i < field.length; i++) {
            char[] columnArr = new char[field.length];
            for (int j = 0; j < field.length; j++) {
                //каждый столбец пишем в columnArr
                columnArr[j] = field[j][i];
            }
            if (Arrays.equals(columnArr, playerWin) || Arrays.equals(columnArr, aiWin)) {
                for (int j = 0; j < columnArr.length; j++) {
                    winArray = new WinCoordinates[GameField.SIZE];
                    winArray[j] = new WinCoordinates(j, i);
                    return true;
                }
            }
        }

        //смотрим диагонали
        //прямая
        char[] arrDiag = new char[field.length];
        for (int i = 0, j = 0; i < field.length; i++, j++) {
            arrDiag[i] = field[i][j];
        }
        if (Arrays.equals(arrDiag, playerWin) || Arrays.equals(arrDiag, aiWin)) {
            for (int i = 0, j = 0; i < field.length; i++, j++) {
                winArray = new WinCoordinates[GameField.SIZE];
                winArray[j] = new WinCoordinates(i, j);
                return true;
            }
        }

        //обратная
        char[] arrBackDiag = new char[field.length];
        for (int i = field.length - 1, j = 0; j < field.length; i--, j++) {
            arrBackDiag[i] = field[i][j];
        }
        if (Arrays.equals(arrBackDiag, playerWin) || Arrays.equals(arrBackDiag, aiWin)) {
            for (int i = field.length - 1, j = 0; j < field.length; i--, j++) {
                winArray = new WinCoordinates[GameField.SIZE];
                winArray[j] = new WinCoordinates(i, j);
                return true;
            }
        }

        return false;
    }

    public boolean isNobodyWin(){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if(field[i][j]==GameField.DEFAULT) return false;
            }
        }
        return true;
    }

    public WinCoordinates[] getWinArray() {
        return winArray;
    }
}
