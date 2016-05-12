/**
 * This class define the exercise 10
 * @author vasap87*/
package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;

public class CaesarСipher extends ConsoleProgram {


	/*default value*/
	private static final long serialVersionUID = 1L;
	
	public void run(){
		println("Программа кодирует вводимое сообщение по Цезарю.");
		int shift = readInt("Введите ключ: ");
		String message = readLine("Введите сообщение: ");
		println("Закодированное сообщение: "+encodeString(message,shift));
	}
	
	/*Метод кодирующий сообщение*/
	private String encodeString(String message, int shift){
		String result="";
		String s = message;
		while(s.length()>0){
			char ch = s.charAt(0);
			if((ch>='a'&&ch<='z')){ 
				if((ch+shift)>'z'){
					int fromBegin = shift - ('z'-ch);
					ch=(char) ('a'-1+fromBegin);
				}else if((ch+shift)<'a'){
					int fromBegin = shift - ('a'-ch);
					ch=(char) ('z'+1+fromBegin);
				}else{
					ch+=shift;
				}	
			}else if((ch>='A'&&ch<='Z')){ 
				if((ch+shift)>'Z'){
					int fromBegin = shift - ('Z'-ch);
					ch=(char) ('A'-1+fromBegin);
				}else if((ch+shift)<'A'){
					int fromBegin = shift - ('A'-ch);
					ch=(char) ('Z'+1+fromBegin);
				}else{
					ch+=shift;
				}	
			}
			else{
				ch+=0;
			}
			s=s.substring(1);
			result+=ch;
		}
		return result;
	}
}
