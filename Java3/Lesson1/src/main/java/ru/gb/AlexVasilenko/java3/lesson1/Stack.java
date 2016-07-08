package ru.gb.AlexVasilenko.java3.lesson1;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 */
public interface Stack<E> {
    //добавление элемента в начало коллекции
    void push(E element) throws StackExeption;
    //вывести и удалить элемент их верха коллекции
    E pop() throws StackExeption;


}
