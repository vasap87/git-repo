/**
 * This class define the exercise 3
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a;

import acm.program.ConsoleProgram;

public class Сapitalize extends ConsoleProgram {
	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа делает любое введённое слово с большой буквы.");
		String str = readLine("Введите слово: ");
		str=str.toLowerCase();
		char firstChar = Character.toUpperCase(str.charAt(0));
		str = str.substring(1);
		String rez = firstChar+str;
		println(rez);
	}
}
