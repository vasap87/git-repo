package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

import java.util.*;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * Класс для сортирова
 */
public class OrderPassengers {
    private static OrderPassengers ourInstance = new OrderPassengers();

    private Map<ArrayList, Integer> sorterTreeMap = new TreeMap();

    public static OrderPassengers getInstance() {
        return ourInstance;
    }

    private OrderPassengers() {
    }

    public Map orderPassengers(ArrayList arrayList) {
        List<Passenger> passengers = arrayList;
        // Iterator iterator = passengers.iterator();
        while (passengers.size() > 0) {
            Passenger p = passengers.get(1);
            int flightTemp = p.getFlightNumber();

        }


        return sorterTreeMap;
    }
}
