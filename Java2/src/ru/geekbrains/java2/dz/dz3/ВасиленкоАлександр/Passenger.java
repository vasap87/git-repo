package ru.geekbrains.java2.dz.dz3.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 15.06.2016.
 * Класс пассажир, определяет новый тип данных пассажир, имеет
 * 2 констркутора.
 */
public class Passenger {
    /**
     * поле для хранения имени пассажира*/
    private String name;
    /**
     * поле для хранения номера документа пассажира*/
    private long documentNumber;
    /**
     * поле для хранения информации о рейсе пассажира*/
    private int flightNumber;

    /**
     * Путой конструктор на всякий случай*/
    public Passenger(){
    }

    /**
     * Конструктор с параметрами
     * @param name имя пассажира, тип данных строка, например "Иванов Иван Иваныч"
     * @param documentNumber  номер документа, тип данных целое число, например 1234567890
     * @param flightNumber  номер рейса пассажира, тип данных целое число, например 1122*/
    public Passenger(String name, long documentNumber, int flightNumber){
        this.name=name;
        this.documentNumber=documentNumber;
        this.flightNumber=flightNumber;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public long getDocumentNumber() {
        return documentNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name + " " + documentNumber;
    }
}
