package ru.gb.AlexVasilenko.java3.lesson1.ex1;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * JAVA3 GeekBrains.ru
 * HomeWork1 - Generics
 */
public class Main {
    public static void main(String[] args) {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        System.out.println(intStack.getSize());
        try {
            intStack.push(29);
            intStack.push(23);
            intStack.push(33);
            intStack.push(323);
        } catch (StackException e) {
            e.printStackTrace();
        }
    }
}
