/**
 * This class define the exercise 9
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class OrdinalForm extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа переводит число в количественный вид.");
		for (int i = 0; i < 10; i++) { //для 10 чисел
			int enterInt = readInt("Введите целое цисло: ");
			println("Вы ввели : "+enterInt + " количественная форма: " +ordinalForm(enterInt));
		}
		
	}
	
	/*метод вычисляет окончание для количественного вида числа*/
	private String ordinalForm(int i){
		String tString = ""+i;
		char lastChar = tString.charAt(tString.length()-1);
		if (i>10){
			char prevLast = tString.charAt(tString.length()-2);
			if(prevLast=='1'&&(lastChar=='1'||lastChar=='2'||lastChar=='3')){
				return i+"th";
			} else if(lastChar=='1'&&prevLast!='1') {
				return i+"st";
			} else if (lastChar=='2'&&prevLast!='1') {
				return i+"nd";
			} else if (lastChar=='3'&&prevLast!='1'){
				return i+"rd";
			} else {
				return i+"th";
			}
		} else {
			if (lastChar=='1') {
				return i+"st";
			} else if (lastChar=='2') {
				return i+"nd";
			} else if (lastChar=='3') {
				return i+"rd";
			} else {
				return i+"th";
			}
		}
	}
}
