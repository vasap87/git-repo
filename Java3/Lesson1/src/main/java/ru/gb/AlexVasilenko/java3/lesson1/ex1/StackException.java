package ru.gb.AlexVasilenko.java3.lesson1.ex1;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * Исключение для класса {@link GenericStack}
 */
public class StackException extends Exception {
    private String message;

    /**
     * Конструктор исключения
     * @param s сообщение об исключении
     * */
    public StackException(String s) {
        message=s;
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
