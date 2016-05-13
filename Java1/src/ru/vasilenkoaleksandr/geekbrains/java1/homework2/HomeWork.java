package ru.vasilenkoaleksandr.geekbrains.java1.homework2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Домашнее задание по второму уроку в курсе Java 1
 * @author Vasilenko Aleksandr
 * */

public class HomeWork {

	public static void main(String[] args) {
		
		//Вывод в консоль отредактирован для лучшей читаемости(добавлены пробелы)
		
		//Создание массива из 1 и 0
		int [] arrOneZero = new int[10];
		for (int i = 0; i < arrOneZero.length; i++) {
			if(i%2>0)arrOneZero[i]=0;
			else arrOneZero[i]=1;
		}
		System.out.println("Первоначальный массив: "+Arrays.toString(arrOneZero));
		
		//замена элементов массива arrOneZero 1 на 0 и 0 на 1
		for (int i = 0; i < arrOneZero.length; i++) {
			if(arrOneZero[i]==0) arrOneZero[i]=1;
			else arrOneZero[i]=0;
		}
		System.out.println("Изменённый массив:     "+Arrays.toString(arrOneZero));
		
		//Создние массива из 8 целых чисел и его заполение
		int arrEight[] = new int[8];
		for (int i = 0, j = 1; i < arrEight.length; i++ , j+=3) {
			arrEight[i]=j;
		}
		System.out.println("Массив 8 элементов:    "+Arrays.toString(arrEight));
		
		//Создание массива и измение его элементов
		int[] mas = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
		System.out.println("Есть массив:           "+Arrays.toString(mas));
		for (int i = 0; i < mas.length; i++) {
			if(mas[i]<6)mas[i]*=2;
		}
		System.out.println("Изменённый массив:     "+Arrays.toString(mas));
		 
		//Создание одномерного массива и поиск максимального и минимального значения
		int[] arrMinMax = {9,4,6,7,-9,19,23,1,2,51,66,-8};
		System.out.println("Задан массив:          "+Arrays.toString(arrMinMax));
		int max=0,min=0; 
		for (int i = 0; i < arrMinMax.length; i++) {
			/* если массив из одного эелемента, этот элемент массива
			 * является и минимальным и максимальным*/
			if(arrMinMax.length==1){
				max=min=arrMinMax[i];
			}
			else{
				if(arrMinMax[i]>=max)max=arrMinMax[i];
				else if(arrMinMax[i]<=min)min=arrMinMax[i];
			}
			
		}
		System.out.println("Минимальное значение "+min+", максимальное "+max);
		
		//простой калькулятор
		Scanner sc = new Scanner(System.in);
		System.out.println("Простой калькулятор");
		System.out.println("введите первое число:");
		double a = sc.nextDouble();
		System.out.println("введите второе число:");
		double b = sc.nextDouble();
		System.out.println("введите операцию(+-*/%):");
		String op = sc.next();
		double rez;
		switch(op){
		case("+"):
			rez = a+b;
			System.out.println(rez);
			break;
		case("-"):
			rez = a-b;
			System.out.println(rez);
			break;
		case ("*"):
			rez = a*b;
			System.out.println(rez);
			break;
		case ("/"):
			if(b!=0){
				rez = a/b;
				System.out.println(rez);
				break;				
			}else{
				System.out.println("Делить на ноль нельзя.");
				break;
			}
			
		case ("%"):
			rez = a%b;
			System.out.println(rez);
			break;
		default:
			System.out.println("Неизвестная операция!");
		}
		sc.close();
		
	}

}
