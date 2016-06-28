package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

import java.util.*;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * Класс для сортировки массива с пассажирами в TreeMap
 */
public class OrderPassengers {
    private static OrderPassengers ourInstance = new OrderPassengers();

    private TreeMap<Integer, List<Passenger>> sorterTreeMap = new TreeMap();

    public static OrderPassengers getInstance() {
        return ourInstance;
    }

    private OrderPassengers() {
    }

    public TreeMap orderPassengers(ArrayList arrayList) {
        List<Passenger> passengers = arrayList;
        // Iterator iterator = passengers.iterator();
        //пока в коллекции есть элементы
        while (passengers.size() > 0) {
            //берём первый эллемент списка
            Passenger p = passengers.get(0);
            //записывает его номер рейса
            int flightTemp = p.getFlightNumber();
            //Создаём коллекцию для хранения пассажиров этого рейса
            List<Passenger> flightPassengers = new ArrayList();
            //Добавляем в него текущего пассажира
            flightPassengers.add(p);
            //Удаляем из коллекции текущего пассажира
            passengers.remove(p);
            //Проходим по остальным элементам коллекции если есть, если нет, то записывает в ТриМап наш ключ и пассажиров
            if (passengers.size() == 0) {
                sorterTreeMap.put(flightTemp, flightPassengers);
                break;
            } else {

                for (int i = 0; i < passengers.size(); i++) {
                    Passenger pTemp = passengers.get(i);
                    //если есть пассажир с таким же номером рейса
                    if (flightTemp == pTemp.getFlightNumber()) {
                        flightPassengers.add(pTemp);
                        passengers.remove(pTemp);
                        i--;
                    }
                }


                sorterTreeMap.put(flightTemp, flightPassengers);
            }

        }

        return sorterTreeMap;
    }

}
