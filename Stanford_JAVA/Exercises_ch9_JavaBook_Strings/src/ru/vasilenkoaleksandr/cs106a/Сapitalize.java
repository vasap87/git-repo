/**
 * This class define the exercise 3
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a;

import acm.program.ConsoleProgram;

public class �apitalize extends ConsoleProgram {
	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("��������� ������ ����� �������� ����� � ������� �����.");
		String str = readLine("������� �����: ");
		str=str.toLowerCase();
		char firstChar = Character.toUpperCase(str.charAt(0));
		str = str.substring(1);
		String rez = firstChar+str;
		println(rez);
	}
}
