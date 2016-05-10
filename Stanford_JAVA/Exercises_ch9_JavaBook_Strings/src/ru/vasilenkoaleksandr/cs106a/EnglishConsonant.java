/**
 * This class define the exercise 1
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a;

import acm.program.ConsoleProgram;

public class EnglishConsonant extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;

	
	public void run(){
		println("Программа согланые английского алфавита.");
		for(char ch='A';ch<='Z';ch++){
			if (isEnglishConsonant(ch))
				println(ch);
			}
	}
	
	
	private boolean isEnglishConsonant(char ch){
		ch = Character.toLowerCase(ch);
		if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
			return false;
		} else return true;
	}
}
