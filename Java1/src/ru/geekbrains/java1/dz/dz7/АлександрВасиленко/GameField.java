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

//    public boolean setShip(int cx, int cy, int l, char dir) {
//        int vx = 0;
//        int vy = 0;
//        if (dir == 'V') vy = 1;
//        if (dir == 'H') vx = 1;
//        if (cx + vx * l > 9) return false;
//        if (cy + vy * l > 9) return false;
//        for (int i = 0; i < l; i++) {
//            if (!checkAround(cx + vx * i, cy + vy * i)) return false;
//        }
//        for (int i = 0; i < l; i++) {
//            field[cy + vy * i][cx + vx * i] = SHIP;
//        }
//        return true;
//    }
//
//    public boolean checkAround(int cx, int cy) {
//        for (int i = cy - 1; i <= cy + 1; i++) {
//            for (int j = cx - 1; j <= cx + 1; j++) {
//                if (i > -1 && j > -1 && i < 10 && j < 10)
//                    if (field[i][j] != WATER) return false;
//            }
//        }
//        return true;
//    }

    //метод постановки одного корабля
    private boolean setShip(int x, int y, int len, char direction) {
        //смотрим, является ли место для корабля подходящим
        if (!isSetShipHere(x, y, len, direction)) return false;
        //размещаем корабль
        if (direction == 'H') {
            for (int i = x; i < x + len; i++) {
                field[i][y] = SHIP;
            }
        }
        if (direction == 'V') {
            for (int i = y; i < y + len; i++) {
                field[x][i] = SHIP;
            }
        }

        return true;
    }

    //метод проверки, можно ли стваить корабль тут и вокруг него будет вода
    private boolean isSetShipHere(int x, int y, int len, char direction) {

        if (direction == 'H') {
            if (x + len > SIZE - 1 || x - 1 < 0 || y - 1 < 0 || y + 1 >= SIZE) return false;
            for (int i = x - 1; i < x + len; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (field[i][j] != WATER) return false;
                }
            }
        }

        if (direction == 'V') {
            if (y + len > SIZE - 1 || y - 1 < 0 || x - 1 < 0 || x + 1 >= SIZE) return false;
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j < y + len; j++) {
                    if (field[i][j] != WATER) return false;
                }
            }
        }
        return true;
    }

    //разобраться
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
    public void strike(int x, int y) {
        if (field[x][y] == WATER) {
            field[x][y] = MISS;
            System.out.println("Промах!");
            return;
        }
        if (field[x][y] == SHIP) {
            field[x][y] = HIT;
            System.out.println("Попадание!");
        }
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


}
