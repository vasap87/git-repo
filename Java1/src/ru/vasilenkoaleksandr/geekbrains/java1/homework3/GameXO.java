package ru.vasilenkoaleksandr.geekbrains.java1.homework3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


/**
 * ����� ������������ ����� ���������� ������ ���� ��������-������*/
public class GameXO {
	
	/*����������� ����� � ��������*/
	private static final int NUM_ROW_COLUM=6;
	/*��� ������ ���������� ������� �������� ������*/
	private static final int WIN_NUM=4;
	/*������ ���� ������*/
	private static final char PLAYER = 'X';
	/*������ ���� ������������� ���������*/
	private static final char AI = '0';
	/*������ � ������ ��-���������*/
	private static final char DEFAULT = '*';
	/*���� ��� ����*/
	private static char[][] field = new char[NUM_ROW_COLUM][NUM_ROW_COLUM];

	/**
	 * ����� ��������� �������������� ��������� �������� ���� */
	private static void setup(){
		for (int i = 0; i < field.length; i++) {
			Arrays.fill(field[i], DEFAULT);
		}
		printTurn();	
	}
	
	/**
	 * ����� ������� � ������� ��������� ���� */
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
	 * ��������� ����*/
	private static void setValue(int x, int y, char ch){
		field[x][y]=ch;
	}
	
	/**
	 * ����� �� ������ � ��� ����*/
	private static boolean isTurn(int x, int y){
		return(field[x][y]!=PLAYER&&field[x][y]!=AI);
	}
	
	/**
	 * ��� ����*/
	private static void turn(char ch){
		int x,y;
		if(ch==PLAYER){ //��� ������
			do{
				System.out.println("��� ���, ������� ���������� � ������� X Y (1-3).");
				x = sc.nextInt();
				y = sc.nextInt();
			}while (!isTurn(x-1, y-1));
			setValue(x-1, y-1, ch);
		}else{	//��� ����������

		aiTurn();	
		}	
	}
	
	//��� ����������
	private static void aiTurn(){
		numAITurn++;
		if(numAITurn==1){
			firstTurn();
		}else {
			otherTurn();
		}
		
		
	}
	
	//������ ��� ����� � ������ ����� ������
	private static void firstTurn(){
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==PLAYER){
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
	
	//��������� ���� ����������
	private static void otherTurn(){
		int x,y;
		do{
			x = rand.nextInt(field.length);
			y = rand.nextInt(field.length);
		}while (!isTurn(x, y));
		setValue(x, y, AI);
		//����� ����� ������������� ��������
	}
	
	private static int addOne(char ch,int i){
		if(ch=='-'){
			return --i;
		}else return ++i;
	}
	/**
	 * �������� ������� �� ���-������*/
	private static boolean isAnybodyWin(){
		
		if(isGorizontal()){
			return true;
		}else if (isVertical()){
			return true;
		}else if(isDiagonal()){
			return true;
		}else if(isBackWardDiagonal()){
			return true;
		}else return false;
	}
	
	//�������� �������� �� ����������
	private static boolean isGorizontal(){
		for (int i = 0; i < field.length; i++) {
			int countP=0;
			int countI=0;
			for (int j = 0; j < field.length; j++) {
				if(field[i][j]==PLAYER){
					countP++;
					countI=0;
					if(countP==WIN_NUM)return true;
				}else if(field[i][j]==AI){
					countI++;
					countP=0;
					if(countI==WIN_NUM)return true;
				}else {
					countI=countP=0;
				}
			}
		}
		return false;
	}
	
	//�������� �������� �� ���������
	private static boolean isVertical(){
		for (int i = 0; i < field.length; i++) {
			int countP=0;
			int countI=0;
			for (int j = 0; j < field.length; j++) {
				if(field[j][i]==PLAYER){
					countP++;
					countI=0;
					if(countP==WIN_NUM)return true;
				}else if(field[j][i]==AI){
					countI++;
					countP=0;
					if(countI==WIN_NUM)return true;
				}else {
					countI=countP=0;
				}
			}
		}
		return false;
	}
	
	//�������� �������� �� ��������� �������
	private static boolean isDiagonal(){
		int countP=0;
		int countI=0;
		for (int i = 0; i <= field.length-WIN_NUM; i++) {
			for (int j = 0; j <= field.length-WIN_NUM; j++) {
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
					for (int k = 1; k <= WIN_NUM; k++) {
						if(ch==field[i+k][j+k]){
							if(ch==PLAYER){
								countP++;
								countI=0;
								if(countP==WIN_NUM)return true;
							}
							else{
								countP=0;
								countI++;
								if(countI==WIN_NUM)return true;
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
	
	//�������� �� �������� ��������� �������
	private static boolean isBackWardDiagonal(){
		int countP=0;
		int countI=0;
		for (int i = field.length-1; i > WIN_NUM-2; i--) { 
			for (int j = 0; j < field.length-WIN_NUM; j++) {
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
					for (int k = 1; k <= WIN_NUM; k++) {
						if(ch==field[i-k][j+k]){
							if(ch==PLAYER){
								countP++;
								countI=0;
								if(countP==WIN_NUM)return true;
							}
							else{
								countP=0;
								countI++;
								if(countI==WIN_NUM)return true;
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
	 * �������� �� �����*/
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
				System.out.println("������� "+((turn%2==1)?"�����":"���������"));
				break;
			}
			if(isNobodyWin()){
				System.out.println("����� �� �������!");
				break;
			}
			turn++;
		}
		

	}
	
	private static int numAITurn=0;
	private static Scanner sc = new Scanner(System.in);
	private static Random rand = new Random();

}
