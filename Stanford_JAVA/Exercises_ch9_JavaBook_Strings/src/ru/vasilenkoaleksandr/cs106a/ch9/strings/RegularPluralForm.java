/**
 * This class define the exercise 7
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class RegularPluralForm extends ConsoleProgram {
	
	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("��������� ��������� �������� ����� � ������������� �����.");
		String s = readLine("������� ����� �� ���������� �����, ��� ��������� ��� �� ������������� �����: ");
		println("�� ����� ����� "+s+" ��� ������������� ����� ����� �����: "+ regularPluralForm(s));
	}
	
	private String regularPluralForm(String s){
		String end1 = s.substring(s.length()-1);
		String end2;
		
		if(s.length()>1){
			end2 = s.substring(s.length()-2);	
		}else 	{
			end2 = " ";
		}
		if (end1.equals("s")||end1.equals("x")||end1.equals("z")||end2.equals("ch")||end2.equals("sh")){
			return s.substring(0, s.length())+"es";
		} else if (end1.equals("y")){
			return s.substring(0, s.length()-1)+"ies";
		} else {
			return s+"s";
		}
	}
}
