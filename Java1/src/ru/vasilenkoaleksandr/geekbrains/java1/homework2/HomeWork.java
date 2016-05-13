package ru.vasilenkoaleksandr.geekbrains.java1.homework2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * �������� ������� �� ������� ����� � ����� Java 1
 * @author Vasilenko Aleksandr
 * */

public class HomeWork {

	public static void main(String[] args) {
		
		//����� � ������� �������������� ��� ������ ����������(��������� �������)
		
		//�������� ������� �� 1 � 0
		int [] arrOneZero = new int[10];
		for (int i = 0; i < arrOneZero.length; i++) {
			if(i%2>0)arrOneZero[i]=0;
			else arrOneZero[i]=1;
		}
		System.out.println("�������������� ������: "+Arrays.toString(arrOneZero));
		
		//������ ��������� ������� arrOneZero 1 �� 0 � 0 �� 1
		for (int i = 0; i < arrOneZero.length; i++) {
			if(arrOneZero[i]==0) arrOneZero[i]=1;
			else arrOneZero[i]=0;
		}
		System.out.println("��������� ������:     "+Arrays.toString(arrOneZero));
		
		//������� ������� �� 8 ����� ����� � ��� ���������
		int arrEight[] = new int[8];
		for (int i = 0, j = 1; i < arrEight.length; i++ , j+=3) {
			arrEight[i]=j;
		}
		System.out.println("������ 8 ���������:    "+Arrays.toString(arrEight));
		
		//�������� ������� � ������� ��� ���������
		int[] mas = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
		System.out.println("���� ������:           "+Arrays.toString(mas));
		for (int i = 0; i < mas.length; i++) {
			if(mas[i]<6)mas[i]*=2;
		}
		System.out.println("��������� ������:     "+Arrays.toString(mas));
		 
		//�������� ����������� ������� � ����� ������������� � ������������ ��������
		int[] arrMinMax = {9,4,6,7,-9,19,23,1,2,51,66,-8};
		System.out.println("����� ������:          "+Arrays.toString(arrMinMax));
		int max=0,min=0; 
		for (int i = 0; i < arrMinMax.length; i++) {
			/* ���� ������ �� ������ ���������, ���� ������� �������
			 * �������� � ����������� � ������������*/
			if(arrMinMax.length==1){
				max=min=arrMinMax[i];
			}
			else{
				if(arrMinMax[i]>=max)max=arrMinMax[i];
				else if(arrMinMax[i]<=min)min=arrMinMax[i];
			}
			
		}
		System.out.println("����������� �������� "+min+", ������������ "+max);
		
		//������� �����������
		Scanner sc = new Scanner(System.in);
		System.out.println("������� �����������");
		System.out.println("������� ������ �����:");
		double a = sc.nextDouble();
		System.out.println("������� ������ �����:");
		double b = sc.nextDouble();
		System.out.println("������� ��������(+-*/%):");
		String op = sc.next();
		double rez;
		switch(op){
		case("+"):
			rez = a+b;
			System.out.println(rez);
			break;
		case("-"):
			rez = a-b;
			System.out.println(rez);
			break;
		case ("*"):
			rez = a*b;
			System.out.println(rez);
			break;
		case ("/"):
			if(b!=0){
				rez = a/b;
				System.out.println(rez);
				break;				
			}else{
				System.out.println("������ �� ���� ������.");
				break;
			}
			
		case ("%"):
			rez = a%b;
			System.out.println(rez);
			break;
		default:
			System.out.println("����������� ��������!");
		}
		sc.close();
		
	}

}
