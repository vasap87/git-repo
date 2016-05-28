package ru.geekbrains.java1.dz.dz7.АлександрВасиленко;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 27.05.2016.
 */
public class GameField {

    private char[][] field; //игровое поле
    private Random rand = new Random();
    public final char WATER = '~'; //вода
    private final char SHIP = 'O';   //корабль
    public final char MISS = '*';   //промах
    private final char HIT = 'X';    //попадание
    public final static int SIZE = 10;    //размер поля
    private boolean isHit;          //Попал ли стрелок по кораблю

    public GameField() {
        //инициализация массива(игрового поля) с водой
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = WATER;
            }
        }
        //расстановка кораблей
        setAllShips();
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
                if (i > -1 && j > -1 && i < 10 && j < 10)
                    if (field[i][j] != WATER) return false;
            }
        }
        return true;
    }

    //печать игрового поля
    public void printField(boolean visible) {
        for (int i = 0; i < 11; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.printf("%2d ", (i + 1));
            for (int j = 0; j < 10; j++) {
                if (visible) {
                    System.out.printf("%2c ", field[i][j]);
                } else {
                    if (field[i][j] == SHIP)
                        System.out.printf("%2c ", '~');
                    else
                        System.out.printf("%2c ", field[i][j]);
                }
            }
            System.out.print('\n');
        }
        System.out.print('\n');
    }

    //Выстрел
    public boolean strike(int x, int y) {
        if (field[x][y] == WATER) {
            field[x][y] = MISS;
            System.out.println("Промах!");
            isHit=false;
            return true;
        }
        if (field[x][y] == SHIP) {
            field[x][y] = HIT;
            System.out.println("Попадание!");
            isHit=true;
            return true;
        }
        if (field[x][y] == HIT) {
           // System.out.println("Ужё вёлся огонь в этом напавлении!\nПоробуйте снова!");
            isHit=false;
            return false;
        }

        if (field[x][y] == MISS) {
            // System.out.println("Ужё вёлся огонь в этом напавлении и безрезультатно!\nПоробуйте снова!");
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
