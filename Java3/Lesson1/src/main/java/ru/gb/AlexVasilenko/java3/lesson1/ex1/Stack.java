package ru.gb.AlexVasilenko.java3.lesson1.ex1;

import java.util.Collection;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * Интерфейс для первого задания по уроку 1, курса
 */
public interface Stack<E> {
    //добавление элемента в начало коллекции
    void push(E element) throws StackException;
    //вывести и удалить элемент их верха коллекции
    E pop() throws StackException;
    //вывести и не удалять верхний эллемент
    E peek();
    //вывести количество элементов стека
    int getSize();
    //вывести
    boolean isEmpty();
    boolean isFull();
    // добавить все элементы @src в стек
    void pushAll(Collection<? extends E> src) throws StackException;
    // вывести из стека все элементы в коллекцию  @dst
    void popAll(Collection<? super E> dst) throws StackException;
}
