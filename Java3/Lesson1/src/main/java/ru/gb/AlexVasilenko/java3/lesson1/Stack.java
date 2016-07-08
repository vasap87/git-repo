package ru.gb.AlexVasilenko.java3.lesson1;

import java.util.Collection;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 */
public interface Stack<E> {
    //добавление элемента в начало коллекции
    void push(E element) throws StackExeption;
    //вывести и удалить элемент их верха коллекции
    E pop() throws StackExeption;
    //вывести и не удалять верхний эллемент
    E peek();
    //вывести количество элементов стека
    int getSize();
    //вывести
    boolean isEmpty();
    boolean isFull();
    // добавить все элементы @src в стек
    void pushAll(Collection<? extends E> src) throws StackExeption;
    // вывести из стека все элементы в коллекцию  @dst
    void popAll(Collection<? super E> dst) throws StackExeption;
}
