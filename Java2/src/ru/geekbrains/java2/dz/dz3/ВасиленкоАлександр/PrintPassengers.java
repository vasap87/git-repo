package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

import java.util.*;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * Класс используется для вывода в консоль информации о рейсах и о пассажирах рейсов
 */
public class PrintPassengers {
    private static PrintPassengers ourInstance = new PrintPassengers();

    public static PrintPassengers getInstance() {
        return ourInstance;
    }

    private PrintPassengers() {
    }

    public void printAllFlightNumbers(TreeMap flight) {

        //Получаем коллекцию ключей
        Set<Integer> flightSet = flight.keySet();
        //Для каждого ключа
        for (Integer i : flightSet) {
            System.out.print("На рейс " + i);
            //для каждого ключа молучаем коллекцию пассажиров
            ArrayList<Passenger> passengers = (ArrayList<Passenger>) flight.get(i);
            System.out.println(" зарегистрированно " + passengers.size() + " пассажиров. Их имена и документы:");
            //сортируем по возрастянию коллекцию пассажиров
            Collections.sort(passengers, new Comparator<Passenger>() {
                @Override
                public int compare(Passenger o1, Passenger o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });
            for (Passenger p : passengers) {
                System.out.println(p.toString());
            }
            System.out.println();
        }
    }
}
