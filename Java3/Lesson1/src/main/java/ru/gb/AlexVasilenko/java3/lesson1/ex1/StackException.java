package ru.gb.AlexVasilenko.java3.lesson1.ex1;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 */
public class StackException extends Exception {
    private String message;

    public StackException(String s) {
        message=s;
    }

    @Override
    public String toString() {
        return "StackException: " + getMessage() +"\n" +super.toString();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
