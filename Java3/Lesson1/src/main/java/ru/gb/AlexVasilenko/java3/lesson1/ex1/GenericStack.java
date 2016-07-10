package ru.gb.AlexVasilenko.java3.lesson1.ex1;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * @see ArrayDeque, {@link Stack}
 */
public class GenericStack<E> implements Stack<E> {
    private ArrayDeque<E> stack;
    private int size;

    /**
     * Конструктор для стека с заданием размера
     * @param size размер стека
     * */
    public GenericStack(int size) {
        this.size = size;
        stack = new ArrayDeque(size);
    }

    /**
     * Конструктор дл стека без параметров,
     * создаёт стек размером 16 элементов
     * */
    public GenericStack() {
        this.size = 16;
        stack = new ArrayDeque();
    }

    /**
     * Добавляет елемент в верх стека, порождает исключение {@link StackException}
     * если нет места для нового элемента
     * @param element добаляемый элемент
     * @throws StackException исключение при полном стеке
     * */
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Коллекция заполнена");
        }
        stack.add(element);
    }

    /**
     * Извлекает верхний эллемент из стека, порождает исключение
     * {@link StackException} если стек пуст.
     * @return верхний эллемет коллекции
     * @throws StackException когда коллекция пуста
     * */
    public E pop() throws StackException {
        if (isEmpty()) {
            throw new StackException("Коллекция пуста");
        }
        return stack.pop();
    }

    /**
     * вызвращает верхний элемент стека
     * @return  верхний эллемент стека*/
    public E peek() {
        return stack.peek();
    }

    /**
     * Возвращает количество элементов в стеке
     * @return int количество элементов в стеке
     * */
    public int getSize() {
        return stack.size();
    }

    /**
     * @return true, если коллекция пуста,
     * false если в стеке есть хотя бы один элемент
     * */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * @return true, если стек заполнен, false если нет
     * */
    public boolean isFull() {
        return getSize() == this.size;
    }


    public void pushAll(Collection<? extends E> src) throws StackException {
        for (E e : src) {
            if (!isFull()) {
                push(e);
            } else throw new StackException("Коллекция заполнена");
        }
    }

    public void popAll(Collection<? super E> dst) throws StackException {
        while (!isEmpty()){
            dst.add(pop());
        }
    }
}
