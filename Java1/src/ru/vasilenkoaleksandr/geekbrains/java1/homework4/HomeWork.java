package ru.vasilenkoaleksandr.geekbrains.java1.homework4;

public class HomeWork {

	public static void main(String[] args) {
		
		//�������� ������� �� �����������
		Empliyee[] arrEmployee = new Empliyee[5];
		arrEmployee[0]=new Empliyee("������ ����", "�����������", "ivanov@ivan.com", "89632581417", 15000, 25);
		arrEmployee[1]=new Empliyee("������ ϸ��", "�������", "petrov@petr.com", "89741472825", 30000, 30);
		arrEmployee[2]=new Empliyee("������� ������", "��������", "sidorov@andry.com", "85461234645", 45000, 31);
		arrEmployee[3]=new Empliyee("�������� ���������", "������������ ������", "medvedev@alex.com", "89622581946", 75000, 33);
		arrEmployee[4]=new Empliyee("������� �����", "�������� ������������", "ivanova@olga.com", "89241235894", 15000, 29);
		
		//������� � ������� ��� �����������, ��� ������� ������ 30
		for (Empliyee empliyee : arrEmployee) {
			if( empliyee.getAge()>30)System.out.println(empliyee);
		}
	}

}

/**
 * ����� ��� �������� �������� � ����� ���������
 * ����� ��������: ���, ���������, email, �������, ��������, �������
 * @author vasilenko.aleksandr*/

public class Empliyee {
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
		return String.format("��������� (���: %s, ���������: %s, Email: %s, �������: %s, ��������: %s, �������: %s]", sFIO, sPost,
				sEmail, sPhone, sSalary, sAge);
	}
	
	
	
	
	
	

}

