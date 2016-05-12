/**
 * This class define the exercise 8
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class PresentParticiple extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("��������� ��������� ������ � ��� Present Simple.");
		String verb = readLine("������� ������ �� ������ ���� ��������: ");
		if (verb.length()>=2){
			println("�� ����� ������ "+ verb + " Present Simple ��� ����� ������� : "
					+ presentParticiple(verb));
		}else println("�� ����� �������� ������.");
	}
	
	/*����� ��������� ����� � present simple �� ��� ��������*/
	private String presentParticiple(String s){
		char last = s.charAt(s.length()-1);
		char prevLast = s.charAt(s.length()-2);
		if (last == 'e'&&!isEnglishConsonant(prevLast)){//���� ��������� � � ����� ��� ���������
			return s.substring(0, s.length()-1)+"ing";
		} else if(!isEnglishConsonant(last)&&isEnglishConsonant(prevLast)){ //���� ��������� 2 ������� - ���������
			return s+last+"ing";
		} else return s+"ing";
		
	}
	
	/*����� ������� true, ���� ch - ���������*/
	private boolean isEnglishConsonant(char ch){
		ch = Character.toLowerCase(ch);
		return (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u');
	}
}
