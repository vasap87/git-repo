package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;


import java.util.Iterator;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * ДЗ по уроку 3 курса Java2
 */
public class Main {
    public static void main(String[] args) {
        //ввод пассажиров
        EnterPassengers.getInstance().addPassengers();
        //вывод данных по рейсам
        List<Passenger> passengers = EnterPassengers.getInstance().getPassengerList();
        Iterator iterator = passengers.iterator();
        while (iterator.hasNext()){
            Passenger p = (Passenger) iterator.next();
            System.out.println(p.getName()+" "+p.getDocumentNumber()+" "+p.getFlightNumber());
        }

    }
}
