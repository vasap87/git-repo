package ru.kotovalex.skyscannerExample.client;


import com.google.gson.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;

import java.net.Socket;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 04.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8899);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Class.forName("com.google.gson.Gson");
            while (true) {
                String s = in.readUTF();
                String[] messages = s.split("\t");
                if (messages[0].equals("json")) {
                    Gson gson = new Gson();
                    try {
                        //парсинг JSON
                        JsonParser parser = new JsonParser();
                        JsonElement jsonElement = parser.parse(messages[1]);
                        JsonObject rootObject = jsonElement.getAsJsonObject();
                        //получить массив plases
                        String placesString = rootObject.get("places").toString();
                        Type type1 = new TypeToken<List<Place>>() {
                        }.getType();
                        List<Place> plases = gson.fromJson(placesString, type1);
                        //Получить массив результатов
                        String resultString = rootObject.get("results").toString();
                        Type type2 = new TypeToken<List<Result>>() {
                        }.getType();
                        List<Result> results = gson.fromJson(resultString, type2);
                        for (Result result : results) {
                            for (Place place : plases) {
                                if(result.getParent_place_id() == place.getPlace_id()) result.setPlace(place);
                            }
                        }
                        //Вывод результата
                        for (Result result : results) {
                           // if(result.is_bookable())
                            if(result.getIndividual_id().equals("27544008"))
                            System.out.println(result);
                        }


                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                        e.getMessage();
                    }
                } else {
                    System.out.println(s);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
