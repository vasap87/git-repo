package ru.vasilenkoaleksandr.geekbrains.java1.homework5;

public class HomeWork {

	public static void main(String[] args) {
		
		//�������� ������ ��������
		Animals [] arrAnimals = new Animals[3];
		arrAnimals[0] = new Dog();
		arrAnimals[1] = new Cat();
		arrAnimals[2] = new Horse();
		
		for (double j = 0.5; j < 3; j+=0.5) {//������ ����������
			//���������, ��� �������� ��������� �� ��� ���� �������.
			for (int i = 0; i < arrAnimals.length; i++) {
				arrAnimals[i].getInfo(j);
				System.out.println();
			}
		}
		


	}

}
