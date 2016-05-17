package ru.vasilenkoaleksandr.geekbrains.java1.homework4;

public class HomeWork {

	public static void main(String[] args) {
		
		//Создание массива из сотрудников
		Employee[] arrEmployee = new Employee[5];
		arrEmployee[0]=new Employee("Иванов Иван", "консультант", "ivanov@ivan.com", "89632581417", 15000, 25);
		arrEmployee[1]=new Employee("Петров Пётр", "инженер", "petrov@petr.com", "89741472825", 30000, 30);
		arrEmployee[2]=new Employee("Сидоров Андрей", "менеджер", "sidorov@andry.com", "85461234645", 45000, 31);
		arrEmployee[3]=new Employee("Медведев Александр", "руководитель группы", "medvedev@alex.com", "89622581946", 75000, 33);
		arrEmployee[4]=new Employee("Иванова Ольга", "помошник руководителя", "ivanova@olga.com", "89241235894", 15000, 29);
		
		//Выводим в консоль тех сотрудников, чей возраст больше 30
		for (Employee employee : arrEmployee) {
			if( employee.getAge()>30)System.out.println(employee);
		}
	}

}

