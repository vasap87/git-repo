/**
 * This class define the exercise 4
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a;

import acm.program.ConsoleProgram;

public class DateString extends ConsoleProgram {
	/*default value*/
	private static final long serialVersionUID = 1L;

	
	public void run(){
		println("Программа выводит дату в строку.");
		int day = 11;
		int month = 5;
		int year = 2016;
		println(dateString(day,month,year));
		
	}
	
	private String dateString(int day,int month, int year){
		String tMonth;
		switch (month){
		case(1):
			tMonth="Jan";
			break;
		case(2):
			tMonth="Feb";
			break;
		case(3):
			tMonth="Mar";
			break;
		case(4):
			tMonth="Apr";
			break;
		case(5):
			tMonth="May";
			break;
		case(6):
			tMonth="Jun";
			break;
		case(07):
			tMonth="Jul";
			break;
		case(8):
			tMonth="Aug";
			break;
		case(9):
			tMonth="Sep";
			break;
		case(10):
			tMonth="Oct";
			break;
		case(11):
			tMonth="Nov";
			break;
		case(12):
			tMonth="Dec";
			break;
		default:tMonth="null";
		break;
		}
		
		String tYear = ""+year;
		tYear=tYear.substring(2);
		return ""+ day+"-"+tMonth+"-"+tYear;
	}
}
