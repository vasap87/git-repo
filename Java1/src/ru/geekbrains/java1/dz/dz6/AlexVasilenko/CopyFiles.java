package ru.geekbrains.java1.dz.dz6.AlexVasilenko;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class CopyFiles {

	public static void main(String args[]){
		//заполняем 2 файла рандомными символами
		try {
			FileOutputStream fout1 = new FileOutputStream("АлександрВасиленко_files/1.txt");
			FileOutputStream fout2 = new FileOutputStream("АлександрВасиленко_files/2.txt");
			writeInFiles(fout1);
			writeInFiles(fout2);
			fout1.close();
			fout2.close();
		} catch (IOException e) {
			System.out.println("Ошибка открытия файла.");
		}

		//копируем из двух файлов в один
		try {
			FileInputStream fin1 = new FileInputStream("АлександрВасиленко_files/1.txt");	//первый файл откуда копируем
			FileInputStream fin2 = new FileInputStream("АлександрВасиленко_files/2.txt");	//второй файл откуда копируем
			FileOutputStream fout = new FileOutputStream("АлександрВасиленко_files/3.txt"); //куда копируем
			copyFromTwoinOne(fout,fin1,fin2);
			fin1.close();
			fin2.close();
			fout.close();
		} catch (IOException e) {
			System.out.println("Ошибка копирования файлов.");
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
