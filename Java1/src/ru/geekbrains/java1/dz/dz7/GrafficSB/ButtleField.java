package ru.geekbrains.java1.dz.dz7.GrafficSB;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by admin on 29.05.2016.
 */
public class ButtleField extends JPanel {
    private char[][] field; //игровое поле
    private Random rand = new Random();
    public final char WATER = '~'; //вода
    private final char SHIP = 'O';   //корабль
    public final char MISS = '*';   //промах
    private final char HIT = 'X';    //попадание
    public final static int SIZE = 10;    //размер поля
    private boolean isHit;          //Попал ли стрелок по кораблю

    public ButtleField() {
        setLayout(new GridLayout(SIZE, SIZE));
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j]=WATER;
            }
        }

        //расстановка кораблей
        setAllShips();

        printField();
    }

    //метод расстановки кораблей
    private void setAllShips() {
        int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1}; //массив для записи длинн кораблей
        char direction;
        for (int i = 0; i < shipLength.length; i++) {
            if (rand.nextBoolean()) direction = 'H';
            else direction = 'V';
            while (!setShip(rand.nextInt(SIZE), rand.nextInt(SIZE), shipLength[i], direction)) ;

        }

    }
    //Метод растановки одной единицы корабля
    public boolean setShip(int x, int y, int len, char dir) {
        int vx = 0;
        int vy = 0;
        if (dir == 'V') vy = 1;
        if (dir == 'H') vx = 1;
        if (x + vx * len > SIZE-1) return false;
        if (y + vy * len > SIZE-1) return false;
        for (int i = 0; i < len; i++) {
            if (!checkAround(x + vx * i, y + vy * i)) return false;
        }
        for (int i = 0; i < len; i++) {
            field[y + vy * i][x + vx * i] = SHIP;
        }
        return true;
    }

    //проверка на отсутсвие неводы рядом
    public boolean checkAround(int x, int y) {
        for (int i = y - 1; i <= y + 1; i++) {
            for (int j = x - 1; j <= x + 1; j++) {
                if (i > -1 && j > -1 && i < SIZE && j < SIZE)
                    if (field[i][j] != WATER) return false;
            }
        }
        return true;
    }

    //вывод игрового поля
    public void printField() {
        removeAll();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                add(new JButton(field[i][j]+""));
            }
        }
    }

    //Выстрел
    public boolean strike(int x, int y) {
        if (field[x][y] == WATER) {
            field[x][y] = MISS;
            isHit=false;
            return true;
        }
        if (field[x][y] == SHIP) {
            field[x][y] = HIT;
            isHit=true;
            return true;
        }
        if (field[x][y] == HIT) {
            isHit=false;
            return false;
        }
        if (field[x][y] == MISS) {
            isHit=false;
            return false;
        }
        return true;

    }

    //проверка есть ли ещё корабли
    public boolean isDefeated() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == SHIP) return false;
            }
        }
        return true;
    }

    public boolean isHit() {
        return isHit;
    }
}
