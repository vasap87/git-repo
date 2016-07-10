package ru.gb.AlexVasilenko.java3.lesson1.ex1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.gb.AlexVasilenko.java3.lesson1.ex2.LengthComparator;

import java.util.ArrayDeque;
import java.util.Collection;

/**
 * Created by vasilenko.aleksandr on 08.07.2016.
 * @see ArrayDeque, {@link Stack}
 */
public class GenericStack<E> implements Stack<E> {

    static Logger logger = LoggerFactory.getLogger(LengthComparator.class);
    private ArrayDeque<E> stack;
    private int size;

    /**
     * Конструктор для стека с заданием размера
     * @param size размер стека
     * */
    public GenericStack(int size) {
        this.size = size;
        stack = new ArrayDeque(size);
        logger.info("Создался стек с размером "+ size);
    }

    /**
     * Конструктор дл стека без параметров,
     * создаёт стек размером 16 элементов
     * */
    public GenericStack() {
        this.size = 16;
        stack = new ArrayDeque();
        logger.info("Создался стек с размером "+ size);
    }

    /**
     * Добавляет елемент в верх стека, порождает исключение {@link StackException}
     * если нет места для нового элемента
     * @param element добаляемый элемент
     * @throws StackException исключение при полном стеке
     * */
    @Override
    public void push(E element) throws StackException {
        if (isFull()) {
            throw new StackException("Коллекция заполнена");
        }
        stack.add(element);
        logger.info("Добавляем к стеку "+ element.toString());
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
        logger.info("Извлечение из стека "+ peek().toString());
        return stack.removeLast();
    }

    /**
     * вызвращает верхний элемент стека
     * @return  верхний эллемент стека*/
    public E peek() {
        logger.info("Извлечение из стека "+ stack.peekLast().toString());
        return stack.peekLast();
    }

    /**
     * Возвращает количество элементов в стеке
     * @return int количество элементов в стеке
     * */
    public int getSize() {
        logger.info("Возврат максимально возможных элементов стека  "+ stack.size());
        return stack.size();
    }

    /**
     * @return true, если коллекция пуста,
     * false если в стеке есть хотя бы один элемент
     * */
    public boolean isEmpty() {
        logger.info("Стек пустой? "+ (getSize() == 0));
        return getSize() == 0;
    }

    /**
     * @return true, если стек заполнен, false если нет
     * */
    public boolean isFull() {
        logger.info("Стек полный? "+ (getSize() == this.size));
        return getSize() == this.size;
    }


    public void pushAll(Collection<? extends E> src) throws StackException {
        for (E e : src) {
            if (!isFull()) {
                push(e);
            } else throw new StackException("Коллекция заполнена");
        }
        logger.info("Добавление эллементов из другой коллекции завершено");
    }

    public void popAll(Collection<? super E> dst) throws StackException {
        while (!isEmpty()){
            dst.add(pop());
        }
        logger.info("Извлечение из коллекции в другую");
    }
}
