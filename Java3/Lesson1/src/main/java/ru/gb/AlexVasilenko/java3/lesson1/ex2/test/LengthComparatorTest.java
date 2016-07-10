package ru.gb.AlexVasilenko.java3.lesson1.ex2.test;

import org.testng.annotations.Test;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.GenericStack;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.StackException;
import ru.gb.AlexVasilenko.java3.lesson1.ex2.LengthComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 * Класс для тестирования компаратора
 * @see LengthComparator
 */
public class LengthComparatorTest {

    @Test
    public void checkLengthComparator() {
        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("bbb");
        strings.add("cd");
        Collections.sort(strings, new LengthComparator());
        String[] arrStrings = strings.toArray(new String[strings.size()]);
        assertEquals("[cd, aaa, bbb]", Arrays.toString(arrStrings));
    }


}
