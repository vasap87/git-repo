package ru.vasilenkoaleksandr.geekbrains.java1.homework4;

public class HomeWork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Empliyee[] arrEmployee = new Empliyee[5];
		arrEmployee[0]=new Empliyee("������ ����", "�����������", "ivanov@ivan.com", "89632581417", 15000, 25);
		arrEmployee[1]=new Empliyee("������ ϸ��", "�������", "petrov@petr.com", "89741472825", 30000, 30);
		arrEmployee[2]=new Empliyee("������� ������", "��������", "sidorov@andry.com", "85461234645", 45000, 31);
		arrEmployee[3]=new Empliyee("�������� ���������", "������������ ������", "medvedev@alex.com", "89622581946", 75000, 33);
		arrEmployee[4]=new Empliyee("������� �����", "�������� ������������", "ivanova@olga.com", "89241235894", 15000, 29);
		
		for (Empliyee empliyee : arrEmployee) {
			if( empliyee.getAge()>30)System.out.println(empliyee);
		}
	}

}
