/**
 * This class define the exercise 6
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class MyIndexOf extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа демонтрирует работу метода IndexOf.");
		String str = "I love Jane, so much!";
		int pos = 4;
		char ch = 'o';
		println(str);
		println("Позиция символа " + ch + " начиная с позиции "+pos+" = "+ myIndexOf(str, ch, pos));
	}
	
	private int myIndexOf(String s, char ch, int i){
		for (int j = i; j < s.length(); j++) {
			char chTemp=s.charAt(j);
			if (chTemp==ch) {
				return j;
			}
			
		}
		return -1;
	}

}
