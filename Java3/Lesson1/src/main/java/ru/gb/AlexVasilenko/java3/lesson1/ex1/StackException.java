package ru.gb.AlexVasilenko.java3.lesson1.ex1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.AlexVasilenko.java3.lesson1.ex2.LengthComparator;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * Исключение для класса {@link GenericStack}
 */
public class StackException extends Exception {
    private static Logger logger = LoggerFactory.getLogger(StackException.class);
    private String message;

    /**
     * Конструктор исключения
     * @param s сообщение об исключении
     * */
    public StackException(String s) {
        message=s;
        logger.info("Создался Exception с сообщением "+message);

    }

    /**
     * Переопределение метода
     * @return String "StackException: " + getMessage()
     * */
    @Override
    public String toString() {
        return "StackException: " + getMessage() +"\n" +super.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
