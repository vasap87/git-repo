package ru.vasilenkoaleksandr.myProjects.XO;

import java.util.Arrays;

/**
 * Created by vasilenko.aleksandr on 06.06.2016.
 */
public class FindTurn {
    private int x;
    private int y;
    private static FindTurn instance;
    private char[][] field = GameField.getInstance().getField();

    //закрытый констркутор
    private FindTurn() {
        this.x=-1;
        this.y=-1;
    }
    //возврат единстенного экземпляра класса
    public static FindTurn getInstance() {
        if (instance==null){
            instance = new FindTurn();
        }
        return instance;
    }

    //смотрим можно ли заблокировать играк в строках
    private boolean isGorizontal() {
        //для каждой строки массива
        for (int i = 0; i < field.length; i++) {
            //если строку можно заблокировать
            if (isBlockInLines(field[i])) {
                for (int j = 0; j < field[i].length; j++) {
                    //записываем координаты места, где можно заблокировать
                    if (field[i][j] == GameField.DEFAULT){
                        this.x=i;
                        this.y=j;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //смотри можно ли заблокирвоать столбец игроку
    private boolean isVertical() {
        for (int i = 0; i < field.length; i++) {
            char[] columnArr = new char[field.length];
            for (int j = 0; j < field.length; j++) {
                //каждый столбец пишем в columnArr
                columnArr[j] = field[j][i];
            }
            //если можно заблокировать в этом массиве ячейку
            if(isBlockInLines(columnArr)){
                for (int j = 0; j < columnArr.length ; j++) {
                    if(columnArr[j] == GameField.DEFAULT){
                        //записываем координаты места, где можно заблокировать
                        this.x = j;
                        this.y = i;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //смотрим можно ли блокировать диагональ
    private boolean isDiagonal() {
        char[] arrDiag = new char[field.length];
        for (int i = 0, j = 0; i < field.length; i++, j++) {
            arrDiag[i] = field[i][j];
        }
        if(isBlockInLines(arrDiag)){
            for (int i = 0, j = 0; i < field.length; i++, j++) {
                if(field[i][j]==GameField.DEFAULT){
                    this.x=i;
                    this.y=j;
                    return true;
                }
            }
        }

        char[] arrBackDiag = new char[field.length];
        for (int i = field.length - 1, j = 0; j < field.length; i--, j++) {
            arrBackDiag[i] = field[i][j];
        }
        if(isBlockInLines(arrBackDiag)){
            for (int i = field.length - 1, j = 0; j < field.length; i--, j++) {
                if(field[i][j]==GameField.DEFAULT){
                    this.x=i;
                    this.y=j;
                    return true;
                }
            }
        }

        return false;
    }


    //смотрим можно ли заблокировать в строке
    private boolean isBlockInLines(char[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == GameField.PLAYER && arr[i] != GameField.AI) count++;
        }
        return count > 1;
    }

    public boolean isBlock(){
        return isGorizontal()||isVertical()||isDiagonal();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
