package ru.vasilenkoaleksandr.geekbrains.java1.homework4;

public class HomeWork {

	public static void main(String[] args) {
		
		//Создание массива из сотрудников
		Empliyee[] arrEmployee = new Empliyee[5];
		arrEmployee[0]=new Empliyee("Иванов Иван", "консультант", "ivanov@ivan.com", "89632581417", 15000, 25);
		arrEmployee[1]=new Empliyee("Петров Пётр", "инженер", "petrov@petr.com", "89741472825", 30000, 30);
		arrEmployee[2]=new Empliyee("Сидоров Андрей", "менеджер", "sidorov@andry.com", "85461234645", 45000, 31);
		arrEmployee[3]=new Empliyee("Медведев Александр", "руководитель группы", "medvedev@alex.com", "89622581946", 75000, 33);
		arrEmployee[4]=new Empliyee("Иванова Ольга", "помошник руководителя", "ivanova@olga.com", "89241235894", 15000, 29);
		
		//Выводим в консоль тех сотрудников, чей возраст больше 30
		for (Empliyee empliyee : arrEmployee) {
			if( empliyee.getAge()>30)System.out.println(empliyee);
		}
	}

}

/**
 * Класс для создания объектов с типом сотрудник
 * Имеет свойства: ФИО, должность, email, телефон, зарплата, возраст
 * @author vasilenko.aleksandr*/

public class Empliyee {
	private String sFIO;
	private String sPost;
	private String sEmail;
	private String sPhone;
	private int sSalary;
	private int sAge;
	
	/**
	 * Контруктор класса Employee
	 * @param sFIO Фамилия Имя Отчество
	 * @param sPost Должность
	 * @param sEmail Email
	 * @param sPhone Телефон
	 * @param sSalary Зарплата
	 * @param sAge Возраст
	 * */
	public Empliyee(String sFIO, String sPost, String sEmail, String sPhone, int sSalary, int sAge) {
		super();
		this.sFIO = sFIO;
		this.sPost = sPost;
		this.sEmail = sEmail;
		this.sPhone = sPhone;
		this.sSalary = sSalary;
		this.sAge = sAge;
	}

	public String getFIO() {
		return sFIO;
	}

	public void setFIO(String sFIO) {
		this.sFIO = sFIO;
	}

	public String getPost() {
		return sPost;
	}

	public void setPost(String sPost) {
		this.sPost = sPost;
	}

	public String getEmail() {
		return sEmail;
	}

	public void setEmail(String sEmail) {
		this.sEmail = sEmail;
	}

	public String getPhone() {
		return sPhone;
	}

	public void setPhone(String sPhone) {
		this.sPhone = sPhone;
	}

	public int getSalary() {
		return sSalary;
	}

	public void setSalary(int sSalary) {
		this.sSalary = sSalary;
	}

	public int getAge() {
		return sAge;
	}

	public void setAge(int sAge) {
		this.sAge = sAge;
	}

	@Override
	public String toString() {
		return String.format("Сотрудник (ФИО: %s, Должность: %s, Email: %s, Телефон: %s, Зарплата: %s, Возраст: %s]", sFIO, sPost,
				sEmail, sPhone, sSalary, sAge);
	}
	
	
	
	
	
	

}

