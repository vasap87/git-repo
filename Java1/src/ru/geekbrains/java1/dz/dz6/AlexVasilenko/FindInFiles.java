package ru.geekbrains.java1.dz.dz6.AlexVasilenko;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FindInFiles {

	public static void main(String[] args) {

		// ������ ������ ������ ��� ����� ������
		String dir = "������������������_files/find/";	//���� � ���������� ������������� ����� �������
		File arrFiles[] = new File[10]; //������ �� 10-�� ������
		
		//��� ��������� ���������� ������� ���������� ���� ���� ����� ���� ������������ ����� File, �� �� ��� �� ����������
		for (int i = 0; i < arrFiles.length; i++) { 
			String str = dir.concat((i + 1) + ".txt");	
			arrFiles[i] = new File(str);
		}
		//�������� �����
		String search = "java";
		System.out.println("����� "+ search+ " ���� � ������: " + findInFiles(arrFiles, search));

	}

	// ����� � ������� ������
	private static String findInFiles(File arrFiles[], String search) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < arrFiles.length; i++) {
			try {
				FileInputStream fin = new FileInputStream(arrFiles[i]);
				int j = 0;
				StringBuilder sb = new StringBuilder();
				do {
					j = fin.read();
					//����� � ���������� ������ StringBuilder ������ ������ �� �����
					sb.append((char)j); 
				} while (j != -1);
				//� ���������� ������ StringBuilder ���� ������ ������� ���������
				int index = sb.toString().toLowerCase().indexOf(search.toLowerCase());
				//���� ���� ����� ��� ����� � �������������� ������
				if (index!=-1) result.append(arrFiles[i].getName()).append(' ');
				fin.close();
			} catch (IOException e) {
				System.out.println("������ �������� �����.");
			}
		}
		return result.toString();
	}

}
