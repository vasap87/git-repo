package ru.gb.AlexVasilenko.java3.lesson1.ex2;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 * Класс сортирвки коллекции строк по длинне строки
 *
 * @see java.util.Comparator
 */
public class LengthComparator implements Comparator<String> {
    static Logger logger = LoggerFactory.getLogger(LengthComparator.class);

    @Override
    public int compare(String o1, String o2) {
        if (o1.length() == o2.length()) {
            logger.warn(o1 + " и " + o2 + " равны по длинне");
            return 0;
        }
        if (o1.length() < o2.length()) {
            logger.warn(o1 + " меньше " + o2 + " по длинне");
            return -1;
        }
        if (o1.length() > o2.length()) {
            logger.warn(o1 + " больше " + o2 + " по длинне");
            return 1;
        }
        logger.warn("сравниваем " + o1 + " и " + o2 + " условние не выполнилось");
        return 0;
    }
}
