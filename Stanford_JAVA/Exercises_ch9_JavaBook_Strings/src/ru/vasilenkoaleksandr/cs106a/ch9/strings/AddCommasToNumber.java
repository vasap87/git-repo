/**
 * This class define the exercise 11
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class AddCommasToNumber extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа разбивает число на разряды.");
		while (true){
			String number = readLine("Введите число: ");
			if (number.length()==0)break;
			println(addCommasToNumdericString(number));
		}
	}
	/*Метод разбивает число на разряды*/
	private String addCommasToNumdericString(String s){
		String result = "";
		for (int i = s.length()-1, j=1; i >=0; i--, j++) {
			char ch = s.charAt(i);
			if(j%3==0&&i!=0){
				result=","+ch+result;
			}else{
				result=ch+result;
			}
		}
		return result;
	}
}
