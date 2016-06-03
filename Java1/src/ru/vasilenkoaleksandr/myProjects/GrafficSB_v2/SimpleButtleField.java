package ru.vasilenkoaleksandr.myProjects.GrafficSB_v2;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 01.06.2016.
 */
public abstract class SimpleButtleField extends JPanel {
    private char[][] emptyField;
    private char[][] shipsField;
    private static final char SHIP = 'O';
    private static final char WATER = '~';
    private static final int SIZE = 10;

    private Random rand = new Random();

    public SimpleButtleField(boolean gameTypeOne) {

        //Настройки JPanel
        setLayout(new GridBagLayout());
        //символьный массив заполняем водой
        emptyField = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                emptyField[i][j] = WATER;
            }
        }
        //Массив с кораблями
        shipsField = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                shipsField[i][j] = WATER;
            }
        }
        //расстановка кораблей
        setAllShips();

        //Вывод пустого игрового поля
        printField(emptyField, gameTypeOne);
        setVisible(true);
    }

    //Расстановка всех кораблей
    private void setAllShips() {
        int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //массив для записи длин кораблей
        char direction;
        for (int i = 0; i < shipLength.length; i++) {
            if (rand.nextBoolean()) direction = 'H';
            else direction = 'V';
            while (!setShip(rand.nextInt(SIZE), rand.nextInt(SIZE), shipLength[i], direction)) ;

        }
    }

    //Метод растановки одной единицы корабля
    private boolean setShip(int x, int y, int len, char dir) {
        int vx = 0;
        int vy = 0;
        if (dir == 'V') vy = 1;
        if (dir == 'H') vx = 1;
        if (x + vx * len > SIZE - 1) return false;
        if (y + vy * len > SIZE - 1) return false;
        for (int i = 0; i < len; i++) {
            if (!checkAround(x + vx * i, y + vy * i)) return false;
        }
        for (int i = 0; i < len; i++) {
            shipsField[y + vy * i][x + vx * i] = SHIP;
        }
        return true;
    }

    //проверка на отсутсвие неводы рядом
    private boolean checkAround(int x, int y) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i > -1 && j > -1 && i < SIZE && j < SIZE)
                    if (shipsField[i][j] != WATER) return false;
            }
        }
        return true;
    }

    public char[][] getEmptyField() {
        return emptyField;
    }

    public  char[][] getShipsField() {
        return shipsField;
    }

    public static int getSIZE() {
        return SIZE;
    }

    //вывод игрового поля
    public abstract void printField(char[][] emptyField, boolean gameType);


}
