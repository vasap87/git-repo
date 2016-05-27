package ru.vasilenkoaleksandr.geekbrains.java1.homework5;

public class Cat extends Animals {

	@Override
	public void run() {
		System.out.println("Кот бежит!");
	}

	@Override
	public void swim() {
		System.out.println("Кот не умеет плавать ):");
	}

	@Override
	public void jump(double i) {
		if (i<=2){
			System.out.println("Кот перепрыгнул препятсвие "+i+" метра!");
		}else {
			System.out.println("кот не допрыгнул :(");
		}
	}

}
