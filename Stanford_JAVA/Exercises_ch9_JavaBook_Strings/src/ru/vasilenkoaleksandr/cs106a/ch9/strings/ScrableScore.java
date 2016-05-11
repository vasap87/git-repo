/**
 * This class define the exercise 5
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class ScrableScore extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;

	public void run(){
		println("Программа считаем очки введённого слова.");
		enterWord = readLine("Введите слово на ангийском языке: ");
		println("Введённое слово "+ enterWord + " содержит очков: " + calcScore(enterWord));
	}
	
	private int calcScore(String s){
		int rez=0;
		String tString=s;
		for (int i = 0; i < s.length(); i++) {
			char ch = tString.charAt(0);
			rez+=letterScore(ch);
			tString=tString.substring(1);
		}
		return rez;
	}
	
	private int letterScore(char ch){
		if(ch=='A'||ch=='E'||ch=='I'||ch=='L'||ch=='N'||
				ch=='O'||ch=='R'||ch=='S'||ch=='T'||ch=='U'){
			return 1;
		} else if(ch=='D'||ch=='G'){
			return 2;
		} else if(ch=='B'||ch=='C'||ch=='M'||ch=='P'){
			return 3;
		} else if(ch=='F'||ch=='H'||ch=='V'||ch=='W'||ch=='Y'){
			return 4;
		} else if(ch=='K'){
			return 5;
		} else if (ch=='J'||ch=='X'){
			return 8;
		} else if (ch=='Q'||ch=='Z'){
			return 10;
		} else return 0;
	}
	
	private String enterWord;
}
