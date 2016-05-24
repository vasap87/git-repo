package ru.geekbrains.java1.dz.dz6.АлександрВасиленко;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FindInFiles {

	public static void main(String[] args) {

		// создаём массив файлов где будем искать
		String dir = "АлександрВасиленко_files/find/";	//путь к директории отностительно корня проекта
		File arrFiles[] = new File[10]; //массив из 10-ти файлов
		
		//для упрощения заполнения массива используем цикл хотя можно было использовать класс File, но мы его не проходимли
		for (int i = 0; i < arrFiles.length; i++) { 
			String str = dir.concat((i + 1) + ".txt");	
			arrFiles[i] = new File(str);
		}
		//Вызываем поиск
		String search = "java";
		System.out.println("слово "+ search+ " есть в файлах: " + findInFiles(arrFiles, search));

	}

	// Поиск в массиве файлов
	private static String findInFiles(File arrFiles[], String search) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < arrFiles.length; i++) {
			try {
				FileInputStream fin = new FileInputStream(arrFiles[i]);
				int j = 0;
				StringBuilder sb = new StringBuilder();
				do {
					j = fin.read();
					//пишем в переменную класса StringBuilder каждый символ из файла
					sb.append((char)j); 
				} while (j != -1);
				//в переменной класса StringBuilder ищем индекс искомой подстроки
				int index = sb.toString().toLowerCase().indexOf(search.toLowerCase());
				//Если есть пишем имя файла в результирующую строку
				if (index!=-1) result.append(arrFiles[i].getName()).append(' ');
				fin.close();
			} catch (IOException e) {
				System.out.println("Ошибка открытия файла.");
			}
		}
		return result.toString();
	}

}
