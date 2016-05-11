/**
 * This class define the exercise 2
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RandomWords extends ConsoleProgram {
	/*default value*/
	private static final long serialVersionUID = 1L;

	private final static int MIN_LETTERS = 3; //минимальное кол-во букв в слове
	private final static int MAX_LETTERS = 6; //максимальное кол-во букв в слове
	
	public void run(){
		println("Программа выводит 5 случайных \"слов\".");
		for(int i=0;i<5;i++){  //5слов
			String result = "";	
			int countLetters = rgen.nextInt(MIN_LETTERS, MAX_LETTERS);	//вычисляем длинну слова
			for (int l=0;l<countLetters;l++){	
				char ch = (char)rgen.nextInt('A', 'Z');	//генерим рандомный символ между A и Z
				result+=ch;	//Собираем слово
			}
			println(result);	//Выводим результат
		}
	}
	
	private RandomGenerator rgen = new RandomGenerator();
}
