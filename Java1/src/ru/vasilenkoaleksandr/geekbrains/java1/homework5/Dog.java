package ru.vasilenkoaleksandr.geekbrains.java1.homework5;

public class Dog extends Animals {

	@Override
	public void run() {
		System.out.println("������ �����!");
	}

	@Override
	public void swim() {
		System.out.println("������ �����!");
	}

	@Override
	public void jump(double i) {
		if(i<=0.5){
			System.out.println("������ ������������ ���������� "+i+" �����!");
		}else {
			System.out.println("������ �� ������ ������������:(");
		}
	}

}
