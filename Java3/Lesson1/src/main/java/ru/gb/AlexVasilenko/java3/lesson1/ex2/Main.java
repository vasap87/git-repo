package ru.gb.AlexVasilenko.java3.lesson1.ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 */
public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("b");
        strings.add("cd");
        Collections.sort(strings, new LengthComparator());
        String[] arrStrings = strings.toArray(new String[strings.size()]);
        System.out.println(Arrays.toString(arrStrings));
    }
}
