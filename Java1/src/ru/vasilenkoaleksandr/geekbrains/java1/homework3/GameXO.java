package ru.vasilenkoaleksandr.geekbrains.java1.homework3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * Класс представляет собой консольную версию игры крестики-нолики*/
public class GameXO {
	
	/*колличество строк и столбцов*/
	private static final int NUM_ROW_COLUM=4;
	/*для победы достаточно столько символов подряд*/
	private static final int WIN_NUM=3;
	/*символ хода игрока*/
	private static final char PLAYER = 'X';
	/*символ хода искуственного интелекта*/
	private static final char AI = '0';
	/*символ в яцейке по-умолчанию*/
	private static final char DEFAULT = '*';
	/*поле для игры*/
	private static char[][] field = new char[NUM_ROW_COLUM][NUM_ROW_COLUM];

	/**
	 * Метод выполняет первоначальную настройку игрового поля */
	private static void setup(){
		for (int i = 0; i < field.length; i++) {
			Arrays.fill(field[i], DEFAULT);
		}
		printTurn();	
	}
	
	/**
	 * Метот выводит в консоль результат хода */
	private static void printTurn(){
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
	 * Установка хода*/
	private static void setValue(int x, int y, char ch){
		field[x][y]=ch;
	}
	
	/**
	 * Можно ли ходить в это поле*/
	private static boolean isTurn(int x, int y){
		return(field[x][y]!=PLAYER&&field[x][y]!=AI);
	}
	
	/**
	 * Ход игры*/
	private static void turn(char ch){
		int x,y;
		if(ch==PLAYER){ //Ход игрока
			do{
				System.out.println("Ваш ход, введите координаты в формате X Y (1-3).");
				x = sc.nextInt();
				y = sc.nextInt();
			}while (!isTurn(x-1, y-1));
			setValue(x-1, y-1, ch);
		}else{	//Ход компьютера

		aiTurn();	
		}	
	}
	
	//Ход компьютера
	private static void aiTurn(){
		numAITurn++;
		if(numAITurn==1){
			firstTurn();
		}else {
			otherTurn();
		}
		
		
	}
	
	//Первых ход рядом с первым ходом игрока
	private static void firstTurn(){
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==PLAYER){
					indexOfRow = i;
					indexOfColumn = j;
					while(true){
						int x=addOne((rand.nextBoolean()?'-':'+'), i);
						int y=addOne((rand.nextBoolean()?'-':'+'), j);
						if(x<field.length&&x>-1&&y<field.length&&y>-1&&x!=i&&y!=j){
							setValue(x,y,AI);
							break;
						}
					}
					break;
				}
			}
		}
	}
	
	//Остальные ходы компьютера
	private static void otherTurn(){
	 if (isPlayerChunk()) {
		 if (findPlaceToBlock()) setAIValue();
		 else  rundTurn();
	 }
		

		//Сдесь будет искусственный интелект
	}
	private static void rundTurn(){
		int x,y;
		do{
			x = rand.nextInt(field.length);
			y = rand.nextInt(field.length);
		}while (!isTurn(x, y));
		setValue(x, y, AI);
	}
	private static boolean isPlayerChunk(){
		for (int n=2;n<WIN_NUM;n++) {
			if(isGorizontal(n)){
				chunk="row";
				return true;
			}else if (isVertical(n)){
				chunk="column";
				return true;
			}else if(isDiagonal(n)){
				chunk="diagonal";
				return true;
			}else if(isBackWardDiagonal(n)){
				chunk="bacwardDiagonal";
				return true;
			}else return false;
		}
		return false;
	}
	
	private static boolean findPlaceToBlock() {


		if (chunk.equals("row")) {
			if (indexOfColumn+1<field.length&&field[indexOfRow][indexOfColumn+1]==DEFAULT) {
				indexOfColumn++;
				return true;
			}
			else if (indexOfColumn-count>=0&&field[indexOfRow][indexOfColumn-count]==DEFAULT) {
				indexOfColumn=indexOfColumn-count;
				return true;
			}
//  		} else if (chunk.equals("column")) {
//			if(indexOfRow+1<field.length&&field[indexOfRow+1][indexOfColumn]==DEFAULT) field[indexOfRow+1][indexOfColumn]=AI;
//		} else if ((chunk.equals("diagonal")) {
//			
//		} else  ;//(chunk==bacwardDiagonal)
		}
		return false;
	}
	
	private static void setAIValue(){
		field[indexOfRow][indexOfColumn]=AI;
	}
	
	private static int addOne(char ch,int i){
		if(ch=='-'){
			return --i;
		}else return ++i;
	}
	/**
	 * Проверка выиграл ли кто-нибудь*/
	private static boolean isAnybodyWin(){
		if(isGorizontal(WIN_NUM)){
			return true;
		}else if (isVertical(WIN_NUM)){
			return true;
		}else if(isDiagonal(WIN_NUM)){
			return true;
		}else if(isBackWardDiagonal(WIN_NUM)){
			return true;
		}else return false;
	}
	
	//проверка выиграша по горизотали
	private static boolean isGorizontal(int n){
		for (int i = 0; i < field.length; i++) {
			int countP=0;
			int countI=0;
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==PLAYER){
					countP++;
					countI=0;
					if(countP==n) {
						count=countP;
						indexOfRow = i;
						indexOfColumn = j;
						return true;
					}
				}else if(field[i][j]==AI){
					countI++;
					countP=0;
					if(countI==n)return true;
				}else {
					countI=countP=0;
				}
			}
		}
		return false;
	}
	
	//проверка выиграша по вертикали
	private static boolean isVertical(int n){
		for (int i = 0; i < field.length; i++) {
			int countP=0;
			int countI=0;
			for (int j = 0; j < field.length; j++) {
				if(field[j][i]==PLAYER){
					countP++;
					countI=0;
					if(countP==n) return true;
				}else if(field[j][i]==AI){
					countI++;
					countP=0;
					if(countI==n)return true;
				}else {
					countI=countP=0;
				}
			}
		}
		return false;
	}
	
	//Проверка выиграша по диагонали матрицы
	private static boolean isDiagonal(int n){
		int countP=0;
		int countI=0;
		for (int i = 0; i <= field.length-n; i++) {
			for (int j = 0; j <= field.length-n; j++) {
				char ch=0;
				if(field[i][j]!=DEFAULT){
					ch=field[i][j];
					if(ch==PLAYER){
						countP++;
						countI=0;
					}
					else{
						countP=0;
						countI++;
					}
					for (int k = 1; k <= n; k++) {
						if(ch==field[i+k][j+k]){
							if(ch==PLAYER){
								countP++;
								countI=0;
								if(countP==n) {
									indexOfRow = i+k;
									indexOfColumn = j+k;
									return true;
								}
							}
							else{
								countP=0;
								countI++;
								if(countI==n)return true;
							}
						}
						else{
							countP=0;
							countI=0;
							ch=0;
							break;
						}
					}
				}
			}
		}
		return false;
	}
	
	//Проверка по обратной диагонали матрицы
	private static boolean isBackWardDiagonal(int n){
		int countP=0;
		int countI=0;
		for (int i = field.length-1; i > n-2; i--) { 
			for (int j = 0; j < field.length-n; j++) {
				char ch=0;
				if(field[i][j]!=DEFAULT){
					ch=field[i][j];
					if(ch==PLAYER){
						countP++;
						countI=0;
					}
					else{
						countP=0;
						countI++;
					}
					for (int k = 1; k <= n; k++) {
						if(ch==field[i-k][j+k]){
							if(ch==PLAYER){
								countP++;
								countI=0;
								if(countP==n) {
									indexOfRow = i-k;
									indexOfColumn = j+k;
									return true;
								}
							}
							else{
								countP=0;
								countI++;
								if(countI==n)return true;
							}
						}
						else{
							countP=0;
							countI=0;
							ch=0;
							break;
						}
					}
				}
			}
		}
		return false;
	}
	
	
	
	/**
	 * Проверка на ничью*/
	private static boolean isNobodyWin(){
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==DEFAULT) return false;
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		setup();
		int turn=1;
		while (true){
			turn((turn%2==1)?PLAYER:AI);
			printTurn();
			if(isAnybodyWin()){
				System.out.println("Победил "+((turn%2==1)?"игрок":"компьютер"));
				break;
			}
			if(isNobodyWin()){
				System.out.println("Никто не победил!");
				break;
			}
			turn++;
		}
		

	}
	
	private static int count=0;
	private static String chunk="";
	private static int indexOfRow=0;
	private static int indexOfColumn=0;
	private static int numAITurn=0;
	private static Scanner sc = new Scanner(System.in);
	private static Random rand = new Random();

}
