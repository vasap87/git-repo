package ru.vasilenkoaleksandr.geekbrains.java1.homework4;

/**
 * ����� ��� �������� �������� � ����� ���������
 * ����� ��������: ���, ���������, email, �������, ��������, �������
 * @author vasilenko.aleksandr*/

public class Employee {
	private String sFIO;
	private String sPost;
	private String sEmail;
	private String sPhone;
	private int sSalary;
	private int sAge;
	
	/**
	 * ���������� ������ Employee
	 * @param sFIO ������� ��� ��������
	 * @param sPost ���������
	 * @param sEmail Email
	 * @param sPhone �������
	 * @param sSalary ��������
	 * @param sAge �������
	 * */
	public Employee(String sFIO, String sPost, String sEmail, String sPhone, int sSalary, int sAge) {
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
		return String.format("��������� (���: %s, ���������: %s, Email: %s, �������: %s, ��������: %s, �������: %s]", sFIO, sPost,
				sEmail, sPhone, sSalary, sAge);
	}
	
	
	
	
	
	

}

