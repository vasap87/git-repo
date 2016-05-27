package ru.vasilenkoaleksandr.geekbrains.java1.homework5;

public class Dog extends Animals {

	@Override
	public void run() {
		System.out.println("Собака бежит!");
	}

	@Override
	public void swim() {
		System.out.println("Собака плывёт!");
	}

	@Override
	public void jump(double i) {
		if(i<=0.5){
			System.out.println("Собака перепрыгнула препятсвие "+i+" метра!");
		}else {
			System.out.println("Собака не смогла перепрыгнуть:(");
		}
	}

}
