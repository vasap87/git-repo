package ru.gb.AlexVasilenko.java3.lesson1.ex2;

import java.util.Comparator;

/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 * Класс сортирвки коллекции строк по длинне строки
 * @see java.util.Comparator
 */
public class LengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if(o1.length()==o2.length()){
            return 0;
        } else if(o1.length()<o2.length()){
            return -1;
        } else if (o1.length()>o2.length()){
            return 1;
        }
        return 0;
    }
}
