package ru.vasilenkoaleksandr.geekbrains.java1.homework1;

/**
 * �������� ������� �� ������� ����� � ����� Java 1
 * @author Vasilenko Aleksandr
 * */

public class HomeWork {
    public static void main(String[] args) {
        //���������� ���������� ���������� � �� �������������
        byte vByte = 2;
        short vSort = 3;
        int vInt = 4;
        long vLong = 5;
        float vFloat = 6.2f;
        double vDouble = 7.123;
        boolean vBool = false;
        char vChar = 's';

        //�������� ������ ������ getRez
        int a=1;
        int b=2;
        int c=3;
        int d=4;
        System.out.println("��������� ���������� "+a+"*("+b+"+("+c+"/"+d+")) = "+getRez(a,b,c,d));

        //�������� ������ ������ isSumBetween10And20
        int e=10;
        int f=2;
        System.out.println("����� ����� "+e+" + "+ f+" ����� 10 � 20? - "+isSumBetween10And20(e,f));

        //�������� ������ ������ ���������� ����������� �� ���
        int year = 2015;
        System.out.println("��� "+year+" "+(isYearLaep(year)?"����������":"�� ����������"));
    }

    /*���� ����� ���������� ��������� ���������� a * (b + (c / d))
     * �.�. ������� �������������, ���� �������� � ��������� ������ double*/
    private static double getRez(int a,int b, int c, int d){
        return a*(b+((double)c/d));
    }

    /*���� ����� ���������� true, ���� ����� ����� a � b � ���������� ����� 10 � 20,
     * � �� ����� ��*/
    private static boolean isSumBetween10And20(int a, int b){
        int sum = a+b;
        return sum>10&&sum<20;
    }

    /*����� ���������� true, ���� ��� ����������, � false ���� �� �����������*/
    private static boolean isYearLaep(int year){
        return ( ((year%4==0)&&(year%100!=0))||year%400==0);
    }

}
