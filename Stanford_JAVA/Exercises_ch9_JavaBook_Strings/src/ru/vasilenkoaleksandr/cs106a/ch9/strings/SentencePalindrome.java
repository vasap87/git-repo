/**
 * This class define the exercise 13
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class SentencePalindrome extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа вычисляет, является ли введённая строка полиндромом.");
		while (true){
			String sentence = readLine("Введите строку: ");
			if(sentence.length()==0)break;
			println("Введённая строка"+(isSentencePolindrome(sentence)?"":" не")+" полиндром");
			
		}
	}
	/*Медод вычисляет, является ли введённая строка подиндромом*/
	private boolean isSentencePolindrome(String s){
		s=s.toLowerCase();
		String onlyLetters = ""; 
		//удаление из строки символов отличных от букв
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isLetter(ch))onlyLetters+=ch;
		}
		//расчёт полиндром полученная строка или нет
		if (onlyLetters.length()==1){
			return true;
		} else{
			for (int i = 0; i < onlyLetters.length()/2; i++) {
				if (onlyLetters.charAt(i)!=onlyLetters.charAt(onlyLetters.length()-1-i)){
					return false;
				}
			}
			return true;
		}
	}
}
