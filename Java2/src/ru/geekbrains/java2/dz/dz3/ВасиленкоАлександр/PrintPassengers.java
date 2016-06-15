package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

import java.util.*;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 */
public class PrintPassengers {
    private static PrintPassengers ourInstance = new PrintPassengers();

    public static PrintPassengers getInstance() {
        return ourInstance;
    }

    private PrintPassengers() {
    }

    public void printAllFlightNumbers(TreeMap flight) {

        Set<Integer> flightSet = flight.keySet();
        for (Integer i: flightSet){
            System.out.println();
        }
    }
}
