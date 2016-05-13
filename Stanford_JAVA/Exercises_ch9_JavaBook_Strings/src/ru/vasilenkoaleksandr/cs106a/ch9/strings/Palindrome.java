/**
 * This class define the exercise 12
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class Palindrome extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа вычисляет, является ли введённое число полиндромным.");
		while (true){
			String word = readLine("Введите слово: ");
			if(word.length()==0)break;
			println("Введённое слово "+word+" "+(isPolindrome(word)?"зеркально.":"не зеркально."));
			
		}
	}
	/*Медод вычисляет, является ли введённое слово подиндромным*/
	private boolean isPolindrome(String s){
		if (s.length()==1){
			return true;
		} else{
			String firstPath = "";
			String lastPath = "";
			for (int i = 0; i < s.length()/2; i++) {
				char fCh = s.charAt(i);
				firstPath+=fCh;
				char lCh = s.charAt(s.length()-1-i);
				lastPath+=lCh;
			}
			return (firstPath.equals(lastPath));
		}
	}
}
