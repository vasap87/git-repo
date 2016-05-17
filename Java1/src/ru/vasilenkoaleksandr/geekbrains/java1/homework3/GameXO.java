package ru.vasilenkoaleksandr.geekbrains.java1.homework3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс представляет собой консольную версию игры крестики-нолики
 * с реализацией искусственного интелекта для блокирования ходов пользователя
 */
public class GameXO {

	/* колличество строк и столбцов */
	private static final int NUM_ROW_COLUM = 4;
	/* для победы достаточно столько символов подряд */
	private static final int WIN_NUM = 3;
	/* символ хода игрока */
	private static final char PLAYER = 'X';
	/* символ хода искуственного интелекта */
	private static final char AI = '0';
	/* символ в яцейке по-умолчанию */
	private static final char DEFAULT = '*';
	/* поле для игры */
	private static char[][] field = new char[NUM_ROW_COLUM][NUM_ROW_COLUM];

	/**
	 * Метод выполняет первоначальную настройку игрового поля
	 */
	private static void setup() {
		for (int i = 0; i < field.length; i++) {
			Arrays.fill(field[i], DEFAULT);
		}
		printTurn();
	}

	/**
	 * Метот выводит в консоль результат хода
	 */
	private static void printTurn() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Установка хода
	 */
	private static void setValue(int x, int y, char ch) {
		field[x][y] = ch;
	}

	/**
	 * Можно ли ходить в это поле
	 */
	private static boolean isTurn(int x, int y) {
		return (field[x][y] != PLAYER && field[x][y] != AI);
	}

	/**
	 * Ход игры
	 */
	private static void turn(char ch) {
		int x, y;
		if (ch == PLAYER) { // Ход игрока
			do {
				System.out.println("Ваш ход, введите координаты в формате X Y (от 1 до "+field.length+" каждое значение, например 2 3)");
				System.out.println("Длинна выигрышной комбинации: "+WIN_NUM);
				x = sc.nextInt();
				y = sc.nextInt();
			} while (!isTurn(x - 1, y - 1));
			setValue(x - 1, y - 1, ch);
		} else { // Ход компьютера
			aiTurn();
		}
	}

	// Ход компьютера
	private static void aiTurn() {
		numAITurn++;
		if (numAITurn == 1) {
			firstTurn();
		} else {
			otherTurn();
		}

	}

	// Первых ход рядом с первым ходом игрока
	private static void firstTurn() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == PLAYER) {
					while (true) {
						int x = addOne((rand.nextBoolean() ? '-' : '+'), i);
						int y = addOne((rand.nextBoolean() ? '-' : '+'), j);
						if (x < field.length && x > -1 && y < field.length && y > -1 && x != i && y != j) {
							setValue(x, y, AI);
							break;
						}
					}
					break;
				}
			}
		}
	}

	// Добавляет или убавляет единицу к целому числу
	private static int addOne(char ch, int i) {
		if (ch == '-') {
			return --i;
		} else
			return ++i;
	}

	// Остальные ходы компьютера
	private static void otherTurn() {
		isFindBlock=true;
		if (isPlayerChunk()) {
			setValue(indexOfRow, indexOfColumn, AI);
		} else
			rundTurn();
		isFindBlock=false;
	}

	// Рандомный ход компьютера
	private static void rundTurn() {
		int x, y;
		do {
			x = rand.nextInt(field.length);
			y = rand.nextInt(field.length);
		} while (!isTurn(x, y));
		setValue(x, y, AI);
	}

	// Есть ли цепочка больше 1 символа у игрока
	private static boolean isPlayerChunk() {
		for (int n = WIN_NUM; n > 0; n--) {
			if (isGorizontal(n)&&findPlaceToBlock(chunk)) {
				return true;
			} else if (isVertical(n)&&findPlaceToBlock(chunk)) {
				return true;
			} else if (isDiagonal(n)&&findPlaceToBlock(chunk)) {
				return true;
			} else if (isBackWardDiagonal(n)&&findPlaceToBlock(chunk)) {
				return true;
			} else
				return false;
		}
		return false;
	}

	private static boolean findPlaceToBlock(String s) {

		if (s.equals("row")) { // Если есть ряд у игрока
			if (indexOfColumn + 1 < field.length && field[indexOfRow][indexOfColumn + 1] == DEFAULT) { // Можно ли поставить справа
				indexOfColumn++;
				return true;
			} else if (indexOfColumn - count >= 0 && field[indexOfRow][indexOfColumn - count] == DEFAULT) {// Можно ли поставить слева
				indexOfColumn = indexOfColumn - count;
				return true;
			} else
				return false;

		} else if (s.equals("column")) { // Если есть столбец у игрока
			if (indexOfRow + 1 < field.length && field[indexOfRow + 1][indexOfColumn] == DEFAULT) { // Можно ли поставить снизу
				indexOfRow++;
				return true;
			} else if (indexOfRow - count >= 0 && field[indexOfRow - count][indexOfColumn] == DEFAULT) {// Можно ли поставить сверху
				indexOfRow = indexOfRow - count;
				return true;
			} else
				return false;
			
		} else if (s.equals("diagonal")) { // Если есть прямая диагональ у игрока
			if ((indexOfRow + 1 < field.length&&indexOfColumn + 1 < field.length) && field[indexOfRow + 1][indexOfColumn+1] == DEFAULT) { // Можно ли поставить снизу
				indexOfRow++;
				indexOfColumn++;
				return true;
			} else if ((indexOfRow - count >= 0&&indexOfColumn - count >= 0) && field[indexOfRow - count][indexOfColumn - count] == DEFAULT) {// Можно ли поставить сверху
				indexOfRow = indexOfRow - count;
				indexOfColumn = indexOfColumn - count;
				return true;
			} else
				return false;
			
		}else if (s.equals("bacwardDiagonal")) { // Если есть обратная диагональ у игрока
			if ((indexOfRow - 1 >= 0&&indexOfColumn + 1 < field.length) && field[indexOfRow - 1][indexOfColumn+1] == DEFAULT) { // Можно ли поставить сверху
				indexOfRow++;
				indexOfColumn++;
				return true;
			} else if ((indexOfRow + count <field.length&&indexOfColumn - count >= 0) && field[indexOfRow + count][indexOfColumn - count] == DEFAULT) {// Можно ли поставить снизу
				indexOfRow = indexOfRow + count;
				indexOfColumn = indexOfColumn - count;
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Проверка, выиграл ди кто-нибудь
	 */
	private static boolean isAnybodyWin() {
		if (isGorizontal(WIN_NUM)) {
			return true;
		} else if (isVertical(WIN_NUM)) {
			return true;
		} else if (isDiagonal(WIN_NUM)) {
			return true;
		} else if (isBackWardDiagonal(WIN_NUM)) {
			return true;
		} else
			return false;
	}

	// если есть цепочка по горизонтали из n-символов вернёт true
	private static boolean isGorizontal(int n) {
		for (int i = 0; i < field.length; i++) {
			int countP = 0;
			int countI = 0;
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == PLAYER) {
					countP++;
					countI = 0;
					if (countP == n) {
						count = countP;
						indexOfRow = i;
						indexOfColumn = j;
						chunk = "row";
						return true;
					}
				} else if (field[i][j] == AI) {
					countI++;
					countP = 0;
					if (countI == n&&!isFindBlock)
						return true;
				} else {
					countI = countP = 0;
				}
			}
		}
		return false;
	}

	// если есть цепочка по вертикали из n-символов вернёт true
	private static boolean isVertical(int n) {
		for (int i = 0; i < field.length; i++) {
			int countP = 0;
			int countI = 0;
			for (int j = 0; j < field.length; j++) {
				if (field[j][i] == PLAYER) {
					countP++;
					countI = 0;
					if (countP == n){
						count = countP;
						indexOfRow = j;
						indexOfColumn = i;
						chunk = "column";
						return true;
					}

				} else if (field[j][i] == AI) {
					countI++;
					countP = 0;
					if (countI == n&&!isFindBlock)
						return true;
				} else {
					countI = countP = 0;
				}
			}
		}
		return false;
	}

	// если есть цепочка по диагонали матрицы из n-символов вернёт true
	private static boolean isDiagonal(int n) {
		int countP = 0;
		int countI = 0;
		for (int i = 0; i <= field.length - n; i++) {
			for (int j = 0; j <= field.length - n; j++) {
				char ch = 0;
				if (field[i][j] != DEFAULT) {
					ch = field[i][j];
					if (ch == PLAYER) {
						countP++;
						countI = 0;
					} else {
						countP = 0;
						countI++;
					}
					for (int k = 1; k <= n; k++) {
						if (ch == field[i + k][j + k]) {
							if (ch == PLAYER) {
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
								if (countI == n&&!isFindBlock)
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
	private static boolean isBackWardDiagonal(int n) {
		int countP = 0;
		int countI = 0;
		for (int i = field.length - 1; i > n - 2; i--) {
			for (int j = 0; j < field.length - n; j++) {
				char ch = 0;
				if (field[i][j] != DEFAULT) {
					ch = field[i][j];
					if (ch == PLAYER) {
						countP++;
						countI = 0;
					} else {
						countP = 0;
						countI++;
					}
					for (int k = 1; k <= n; k++) {
						if (ch == field[i - k][j + k]) {
							if (ch == PLAYER) {
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
								if (countI == n&&!isFindBlock)
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

	/**
	 * Проверка на ничью
	 */
	private static boolean isNobodyWin() {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j] == DEFAULT)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		setup();
		int turn = 1;
		while (true) {
			turn((turn % 2 == 1) ? PLAYER : AI);
			printTurn();
			if (isAnybodyWin()) {
				System.out.println("Победил " + ((turn % 2 == 1) ? "игрок" : "компьютер"));
				break;
			}
			if (isNobodyWin()) {
				System.out.println("Никто не победил!");
				break;
			}
			turn++;
		}

	}

	private static int count = 0;
	private static String chunk = "";
	private static boolean isFindBlock;
	private static int indexOfRow = 0;
	private static int indexOfColumn = 0;
	private static int numAITurn = 0;
	private static Scanner sc = new Scanner(System.in);
	private static Random rand = new Random();

}
