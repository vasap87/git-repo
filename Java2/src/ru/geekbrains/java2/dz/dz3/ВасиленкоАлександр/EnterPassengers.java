package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * Класс для ввода пассажиров, может быть создан только один экземпляр класса.
 * Используется для ввода пассажиров и возврата объекта ArrayList
 */
public class EnterPassengers {

    private ArrayList<Passenger> passengerList = new ArrayList<>();

    private static EnterPassengers ourInstance = new EnterPassengers();

    public static EnterPassengers getInstance() {
        return ourInstance;
    }

    private EnterPassengers() {
    }

    /**
     * Метод */
    public void addPassengers() {
        //буферридер используется в связи неадекватным поведением Scanner в цикле при вводе неверных данных
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Ввод пассажиров:");

                String name = "";
                long documentNumber = 0;
                int flightNumber = 0;

                if (name.equals("")) {
                    System.out.print("Имя: ");
                    name = reader.readLine();
                }

                try {
                    System.out.print("Номер документа: ");
                    documentNumber = Long.parseLong(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Номер документа может содержать только цифры!");
                    continue;
                }

                try {
                    System.out.print("Номер рейса: ");
                    flightNumber = Integer.parseInt(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Номер рейса может содержать только цифры!");
                    continue;
                }

                passengerList.add(new Passenger(name,documentNumber,flightNumber));

                System.out.println("Будут ещё пассажиры? 0 - нет, остальное - будут.");
                String result = reader.readLine();
                if (result.equals("0")) {
                    System.out.println("Распознан конец ввода пассажиров.");
                    break;
                } else {
                    System.out.println();
                }

            }
        } catch (IOException e) {
            System.out.println("Ошибка буферридора.");
        }

    }

    public ArrayList<Passenger> getPassengerList() {
        return passengerList;
    }
}
