package ru.vasilenkoaleksandr.myProjects.XO;

import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 03.06.2016.
 * Класс для хода компьютера
 */
public class AITurn {

    private Random rand = new Random();
    private static AITurn instance;

    private char[][] field = GameField.getInstance().getField();


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
        numAITurn++;
        if (numAITurn == 1) {
            firstTurn();
        } else {
            otherTurn();
        }

    }

    /**
     * Установка хода
     */
    private void setValue(int x, int y, char ch) {
        field[x][y] = ch;
    }

    /**
     * Можно ли ходить в это поле
     */
    private boolean isTurn(int x, int y) {
        return (field[x][y] == GameField.DEFAULT);
    }

    // Первых ход рядом с первым ходом игрока
    private void firstTurn() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == GameField.PLAYER) {
                    while (true) {
                        int x = addOne((rand.nextBoolean() ? '-' : '+'), i);
                        int y = addOne((rand.nextBoolean() ? '-' : '+'), j);
                        if (x < field.length && x > -1 && y < field.length && y > -1 && x != i && y != j) {
                            setValue(x, y, GameField.AI);
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    // Добавляет или убавляет единицу к целому числу
    private int addOne(char ch, int i) {
        if (ch == '-') {
            return --i;
        } else
            return ++i;
    }

    // Остальные ходы компьютера
    private void otherTurn() {
        isFindBlock = true;
        if (isPlayerChunk()) {
            setValue(indexOfRow, indexOfColumn, GameField.AI);
        } else
            rundTurn();
        isFindBlock = false;
    }

    // Рандомный ход компьютера
    private void rundTurn() {
        int x, y;
        do {
            x = rand.nextInt(field.length);
            y = rand.nextInt(field.length);
        } while (!isTurn(x, y));
        setValue(x, y, GameField.AI);
    }

    // Есть ли цепочка больше 1 символа у игрока
    private boolean isPlayerChunk() {
        for (int n = 2; n < GameField.WIN; n++) {
            if (isGorizontal(n) && findPlaceToBlock(chunk)) {
                return true;
            } else if (isVertical(n) && findPlaceToBlock(chunk)) {
                return true;
            } else if (isDiagonal(n) && findPlaceToBlock(chunk)) {
                return true;
            } else if (isBackWardDiagonal(n) && findPlaceToBlock(chunk)) {
                return true;
            }
        }
        return false;
    }

    private boolean findPlaceToBlock(String s) {

        if (s.equals("row")) { // Если есть ряд у игрока
            if (indexOfColumn + 1 < field.length && field[indexOfRow][indexOfColumn + 1] == GameField.DEFAULT) { // Можно ли поставить справа
                indexOfColumn++;
                return true;
            } else if (indexOfColumn - count >= 0 && field[indexOfRow][indexOfColumn - count] == GameField.DEFAULT) {// Можно ли поставить слева
                indexOfColumn = indexOfColumn - count;
                return true;
            } else
                return false;

        } else if (s.equals("column")) { // Если есть столбец у игрока
            if (indexOfRow + 1 < field.length && field[indexOfRow + 1][indexOfColumn] == GameField.DEFAULT) { // Можно ли поставить снизу
                indexOfRow++;
                return true;
            } else if (indexOfRow - count >= 0 && field[indexOfRow - count][indexOfColumn] == GameField.DEFAULT) {// Можно ли поставить сверху
                indexOfRow = indexOfRow - count;
                return true;
            } else
                return false;

        } else if (s.equals("diagonal")) { // Если есть прямая диагональ у игрока
            if ((indexOfRow + 1 < field.length && indexOfColumn + 1 < field.length) && field[indexOfRow + 1][indexOfColumn + 1] == GameField.DEFAULT) { // Можно ли поставить снизу
                indexOfRow++;
                indexOfColumn++;
                return true;
            } else if ((indexOfRow - count >= 0 && indexOfColumn - count >= 0) && field[indexOfRow - count][indexOfColumn - count] == GameField.DEFAULT) {// Можно ли поставить сверху
                indexOfRow = indexOfRow - count;
                indexOfColumn = indexOfColumn - count;
                return true;
            } else
                return false;

        } else if (s.equals("bacwardDiagonal")) { // Если есть обратная диагональ у игрока
            if ((indexOfRow - 1 >= 0 && indexOfColumn + 1 < field.length) && field[indexOfRow - 1][indexOfColumn + 1] == GameField.DEFAULT) { // Можно ли поставить сверху
                indexOfRow++;
                indexOfColumn++;
                return true;
            } else if ((indexOfRow + count < field.length && indexOfColumn - count >= 0) && field[indexOfRow + count][indexOfColumn - count] == GameField.DEFAULT) {// Можно ли поставить снизу
                indexOfRow = indexOfRow + count;
                indexOfColumn = indexOfColumn - count;
                return true;
            } else
                return false;
        }
        return false;
    }


    // если есть цепочка по горизонтали из n-символов вернёт true
    public boolean isGorizontal(int n) {
        for (int i = 0; i < field.length; i++) {
            int countP = 0;
            int countI = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[i][j] == GameField.PLAYER) {
                    countP++;
                    countI = 0;
                    if (countP == n) {
                        count = countP;
                        indexOfRow = i;
                        indexOfColumn = j;
                        chunk = "row";
                        return true;
                    }
                } else if (field[i][j] == GameField.AI) {
                    countI++;
                    countP = 0;
                    if (countI == n && !isFindBlock)
                        return true;
                } else {
                    countI = countP = 0;
                }
            }
        }
        return false;
    }

    // если есть цепочка по вертикали из n-символов вернёт true
    public boolean isVertical(int n) {
        for (int i = 0; i < field.length; i++) {
            int countP = 0;
            int countI = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[j][i] == GameField.PLAYER) {
                    countP++;
                    countI = 0;
                    if (countP == n) {
                        count = countP;
                        indexOfRow = j;
                        indexOfColumn = i;
                        chunk = "column";
                        return true;
                    }

                } else if (field[j][i] == GameField.AI) {
                    countI++;
                    countP = 0;
                    if (countI == n && !isFindBlock)
                        return true;
                } else {
                    countI = countP = 0;
                }
            }
        }
        return false;
    }

    // если есть цепочка по диагонали матрицы из n-символов вернёт true
    public boolean isDiagonal(int n) {
        int countP = 0;
        int countI = 0;
        for (int i = 0; i <= field.length - n; i++) {
            for (int j = 0; j <= field.length - n; j++) {
                char ch = 0;
                if (field[i][j] != GameField.DEFAULT) {
                    ch = field[i][j];
                    if (ch == GameField.PLAYER) {
                        countP++;
                        countI = 0;
                    } else {
                        countP = 0;
                        countI++;
                    }
                    for (int k = 1; k <= n; k++) {
                        if (ch == field[i + k][j + k]) {
                            if (ch == GameField.PLAYER) {
                                countP++;
                                countI = 0;
                                if (countP == n) {
                                    count = countP;
                                    indexOfRow = i + k;
                                    indexOfColumn = j + k;
                                    chunk = "diagonal";
                                    return true;
                                }
                            } else {
                                countP = 0;
                                countI++;
                                if (countI == n && !isFindBlock)
                                    return true;
                            }
                        } else {
                            countP = 0;
                            countI = 0;
                            ch = 0;
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    // если есть цепочка по обратной диагонали матрицы из n-символов вернёт true
    public boolean isBackWardDiagonal(int n) {
        int countP = 0;
        int countI = 0;
        for (int i = field.length - 1; i > n - 2; i--) {
            for (int j = 0; j < field.length - n; j++) {
                char ch = 0;
                if (field[i][j] != GameField.DEFAULT) {
                    ch = field[i][j];
                    if (ch == GameField.PLAYER) {
                        countP++;
                        countI = 0;
                    } else {
                        countP = 0;
                        countI++;
                    }
                    for (int k = 1; k <= n; k++) {
                        if (ch == field[i - k][j + k]) {
                            if (ch == GameField.PLAYER) {
                                countP++;
                                countI = 0;
                                if (countP == n) {
                                    count = countP;
                                    indexOfRow = i - k;
                                    indexOfColumn = j + k;
                                    chunk = "bacwardDiagonal";
                                    return true;
                                }
                            } else {
                                countP = 0;
                                countI++;
                                if (countI == n && !isFindBlock)
                                    return true;
                            }
                        } else {
                            countP = 0;
                            countI = 0;
                            ch = 0;
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }


    private static int count = 0;
    private static String chunk = "";
    private static boolean isFindBlock;
    private static int indexOfRow = 0;
    private static int indexOfColumn = 0;
    private static int numAITurn = 0;


}
