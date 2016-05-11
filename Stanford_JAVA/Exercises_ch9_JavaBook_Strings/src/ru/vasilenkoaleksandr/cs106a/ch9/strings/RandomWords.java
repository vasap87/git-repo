/**
 * This class define the exercise 2
 * @author vasap87*/

package ru.vasilenkoaleksandr.cs106a.ch9.strings;

import acm.program.ConsoleProgram;
import acm.util.RandomGenerator;

public class RandomWords extends ConsoleProgram {
	/*default value*/
	private static final long serialVersionUID = 1L;

	private final static int MIN_LETTERS = 3; //����������� ���-�� ���� � �����
	private final static int MAX_LETTERS = 6; //������������ ���-�� ���� � �����
	
	public void run(){
		println("��������� ������� 5 ��������� \"����\".");
		for(int i=0;i<5;i++){  //5����
			String result = "";	
			int countLetters = rgen.nextInt(MIN_LETTERS, MAX_LETTERS);	//��������� ������ �����
			for (int l=0;l<countLetters;l++){	
				char ch = (char)rgen.nextInt('A', 'Z');	//������� ��������� ������ ����� A � Z
				result+=ch;	//�������� �����
			}
			println(result);	//������� ���������
		}
	}
	
	private RandomGenerator rgen = new RandomGenerator();
}
