package ex1.test;

import ru.gb.AlexVasilenko.java3.lesson1.ex1.GenericStack;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.StackException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.assertEquals;


/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 * <p>
 * Тест на создание стека из трёх эллементов и
 * их последующее изавлечение
 */
public class GenericStackTest {


    @Test
    public void checkGenericStackPopDirection() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
            for (int i = 3; i > 0; i--) {
                assertEquals(i, Math.toIntExact(intStack.pop()));
            }
        } catch (StackException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void checkGenericStackSize() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(3, intStack.getSize());
    }

    @Test
    public void checkGenericStackFull() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(true, intStack.isFull());
    }

    /**
     * @throws StackException
     */
    @Test(expected = StackException.class)
    public void checkGenericStackPush() throws StackException {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
            intStack.push(4);
        } catch (StackException e) {
            throw new StackException("Тестовый ексепшн");
        }
    }

    @Test
    public void checkGenericStackPeek() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(3, Math.toIntExact(intStack.peek()));
    }

    @Test
    public void checkGenericStackPushAll() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        Collection<Integer> intColl = new ArrayList<>();
        intColl.add(1);
        intColl.add(2);
        intColl.add(3);
        try {
            intStack.pushAll(intColl);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(3, Math.toIntExact(intStack.getSize()));
    }

    /**
     * @throws StackException
     */
    @Test(expected = StackException.class)
    public void checkGenericStackPushAllException() throws StackException {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        Collection<Integer> intColl = new ArrayList<>();
        intColl.add(1);
        intColl.add(2);
        intColl.add(3);
        intColl.add(4);
        try {
            intStack.pushAll(intColl);
        } catch (StackException e) {
            throw new StackException("Тестовое исключение");
        }
    }

    @Test
    public void checkGenericStackPopAll() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        Collection<Integer> intColl = new ArrayList<>();
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
            intStack.popAll(intColl);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(0, Math.toIntExact(intStack.getSize()));
    }
}
