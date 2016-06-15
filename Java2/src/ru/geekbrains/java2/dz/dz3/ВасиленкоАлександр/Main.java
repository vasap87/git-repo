package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;


import java.util.*;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * ДЗ по уроку 3 курса Java2
 */
public class Main {
    public static void main(String[] args) {
        //ввод пассажиров
        EnterPassengers.getInstance().addPassengers();
        ArrayList<Passenger> passengers = EnterPassengers.getInstance().getPassengerList();
        //Сортировка по номеру рейса
        TreeMap treeMapPassengers = OrderPassengers.getInstance().orderPassengers(passengers);
        //вывод на печать
        PrintPassengers.getInstance().printAllFlightNumbers(treeMapPassengers);

    }
}
