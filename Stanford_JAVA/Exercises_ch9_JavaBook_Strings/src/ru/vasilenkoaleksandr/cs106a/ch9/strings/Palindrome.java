/**
 * This class define the exercise 12
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class Palindrome extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("��������� ���������, �������� �� �������� ����� ������������.");
		while (true){
			String word = readLine("������� �����: ");
			if(word.length()==0)break;
			println("�������� ����� "+word+" "+(isPolindrome(word)?"���������.":"�� ���������."));
			
		}
	}
	/*����� ���������, �������� �� �������� ����� ������������*/
	private boolean isPolindrome(String s){
		s=s.toLowerCase();
		if (s.length()==1){
			return true;
		} else{
			for (int i = 0; i < s.length()/2; i++) {
				if(s.charAt(i)!=s.charAt(s.length()-1-i)){
					return false;
				}
			}
			return true;
		}
	}
}
