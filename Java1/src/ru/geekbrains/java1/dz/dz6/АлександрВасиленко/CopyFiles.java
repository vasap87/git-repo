package ru.geekbrains.java1.dz.dz6.������������������;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CopyFiles {

	public static void main(String args[]){
		//��������� 2 ����� ���������� ���������
		try {
			FileOutputStream fout1 = new FileOutputStream("������������������_files/1.txt");
			FileOutputStream fout2 = new FileOutputStream("������������������_files/2.txt");
			writeInFiles(fout1);
			writeInFiles(fout2);
			fout1.close();
			fout2.close();
		} catch (IOException e) {
			System.out.println("������ �������� �����.");
		}
		
		//�������� �� ���� ������ � ����
		try {
			FileInputStream fin1 = new FileInputStream("������������������_files/1.txt");	//������ ���� ������ ��������
			FileInputStream fin2 = new FileInputStream("������������������_files/2.txt");	//������ ���� ������ ��������
			FileOutputStream fout = new FileOutputStream("������������������_files/3.txt"); //���� ��������
			copyFromTwoinOne(fout,fin1,fin2);
			fin1.close();
			fin2.close();
			fout.close();
		} catch (IOException e) {
			System.out.println("������ ����������� ������.");
		}
		
	}
	
	//����� ��������� ������� � ����
	private static void writeInFiles(FileOutputStream fout){
		for (int i = 1; i < 150; i++) {
			char ch = (char) (rand.nextInt(26)+'A');
			try {
				if(i%149==0){ //� ����� ��������� ������� ������
					fout.write('\n');
				}else{
					fout.write(ch);
				}
				
			} catch (IOException e) {
				System.out.println("������ ������ � ����.");
			}
		}
	}
	
	
	//����������� �� ���� ������ � ����
	private static void copyFromTwoinOne(FileOutputStream fout, FileInputStream fin1, FileInputStream fin2){
		int i;
		try {
			do { //����������� ������� �����
				i = fin1.read();
				if(i!=-1)fout.write(i);
			} while (i!=-1);
			do { //����������� ������� �����
				i = fin2.read();
				if(i!=-1)fout.write(i);
			} while (i!=-1);
		} catch (IOException e) {
			System.out.println("������ ����������� �� ����� � ����");
		}
	}

	private static Random rand = new Random();
}
