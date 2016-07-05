package ru.kotovalex.skyscannerExample.client;

/**
 * Created by vasilenko.aleksandr on 05.07.2016.
 */
public class Place {
    private int place_id;
    private String city_name;
    private String country_name;

    public int getPlace_id() {
        return place_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    @Override
    public String toString() {
        return "{ place_id: " + place_id +
                " city_name: " + city_name +
                " country_name: " + country_name + "}";
    }
}
