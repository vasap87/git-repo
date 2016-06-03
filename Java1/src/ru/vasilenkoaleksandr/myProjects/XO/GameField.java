package ru.vasilenkoaleksandr.myProjects.XO;

import javax.swing.*;

/**
 * Created by vasilenko.aleksandr on 02.06.2016.
 * SingleTon pattern: this instance is uniq.
 */
public class GameField  {

    private char[][] field;

    private static GameField instance;

    public static GameField getInstance(){
        if(instance==null) {
            instance = new GameField();
        }
        return instance;
    }

    private GameField(){
        field = new char[5][5];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j]=' ';
            }
        }
    }

    public boolean turn(int x,int y, char ch){
        if(field[x][y]==' '){
            field[x][y]=ch;
            return true;
        }
        return false;
    }

    public char[][] getField() {
        return field;
    }
}
