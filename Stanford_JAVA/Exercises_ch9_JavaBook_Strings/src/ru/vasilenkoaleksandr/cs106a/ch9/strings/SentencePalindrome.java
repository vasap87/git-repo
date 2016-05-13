/**
 * This class define the exercise 13
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class SentencePalindrome extends ConsoleProgram {

	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("��������� ���������, �������� �� �������� ������ �����������.");
		while (true){
			String sentence = readLine("������� ������: ");
			if(sentence.length()==0)break;
			println("�������� ������"+(isSentencePolindrome(sentence)?"":" ��")+" ���������");
			
		}
	}
	/*����� ���������, �������� �� �������� ������ �����������*/
	private boolean isSentencePolindrome(String s){
		s=s.toLowerCase();
		String onlyLetters = ""; 
		//�������� �� ������ �������� �������� �� ����
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isLetter(ch))onlyLetters+=ch;
		}
		//������ ��������� ���������� ������ ��� ���
		if (onlyLetters.length()==1){
			return true;
		} else{
			for (int i = 0; i < onlyLetters.length()/2; i++) {
				if (onlyLetters.charAt(i)!=onlyLetters.charAt(onlyLetters.length()-1-i)){
					return false;
				}
			}
			return true;
		}
	}
}
