/**
 * This class define the exercise 8
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class PresentParticiple extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа переводит глагол в вид Present Simple.");
		String verb = readLine("Веедите глагол не меньше двух символов: ");
		if (verb.length()>=2){
			println("Вы ввели глагол "+ verb + " Present Simple для этого глагола : "
					+ presentParticiple(verb));
		}else println("Вы ввели неверный глагол.");
	}
	
	/*метод переводит слово в present simple по трём правилам*/
	private String presentParticiple(String s){
		char last = s.charAt(s.length()-1);
		char prevLast = s.charAt(s.length()-2);
		if (last == 'e'&&!isEnglishConsonant(prevLast)){//если последняя е и перед ней согласная
			return s.substring(0, s.length()-1)+"ing";
		} else if(!isEnglishConsonant(last)&&isEnglishConsonant(prevLast)){ //если последние 2 символа - согласные
			return s+last+"ing";
		} else return s+"ing";
		
	}
	
	/*Метот выводит true, если ch - согласная*/
	private boolean isEnglishConsonant(char ch){
		ch = Character.toLowerCase(ch);
		return (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u');
	}
}
