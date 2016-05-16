/**
 * Этот класс - решение задачи номер 2 из проблем сета к лекции 13*/

package ru.vasilenkoaleksandr.cs106a.lecture13_problemset;

import java.util.Scanner;

public class RemoveAllOccurrences{


	public static void main(String[] args) {
		
		System.out.println("Программа из строки удаляет необходимые символы.");
		System.out.println("Введите строку:");
		String str = sc.nextLine();
		System.out.println("Введите символ для поиска и удаления:");
		char ch = sc.next().charAt(0);
		System.out.println("Результат: "+ removeAllOccurrences(str,ch));
	} 
	
	private static String removeAllOccurrences(String s,char ch){
		String tString=s;
		String result="";
		while(true){
			int index = tString.indexOf(ch);
			if(index==-1){
				result+=tString;
				break;
			}
			result+=tString.substring(0, index);
			tString=tString.substring(index+1);
		}
		return result;
		
	}

	private static Scanner sc = new Scanner(System.in);
}
