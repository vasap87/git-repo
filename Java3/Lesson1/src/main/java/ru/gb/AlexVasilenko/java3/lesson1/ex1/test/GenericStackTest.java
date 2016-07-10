package ru.gb.AlexVasilenko.java3.lesson1.ex1.test;

import org.testng.annotations.Test;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.GenericStack;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.StackException;

import static org.testng.Assert.assertEquals;

/**
 * Created by vasilenko.aleksandr on 10.07.2016.
 *
 * Тест на создание стека из трёх эллементов и
 * их последующее изавлечение
 */
public class GenericStackTest {

    private int sum = 0;

    /**
     * @throws StackException
     * */
    @Test(expectedExceptions = StackException.class)
    public void checkGenericStackPartOne() throws StackException {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
        } catch (StackException e) {
            e.printStackTrace();
        }
        assertEquals(intStack.getSize(),3);
        assertEquals(intStack.isFull(), true);
        try {
            while (!intStack.isEmpty()) {
                switch (intStack.peek()) {
                    case 1: {
                        sum+=intStack.peek();
                        assertEquals(Math.toIntExact(intStack.pop()), 1);
                        break;
                    }
                    case 2: {
                        sum+=intStack.peek();
                        assertEquals(Math.toIntExact(intStack.pop()), 2);
                        break;
                    }
                    case 3: {
                        sum+=intStack.peek();
                        assertEquals(Math.toIntExact(intStack.pop()), 3);
                        break;
                    }
                }
            }
            intStack.pop();
        } catch (StackException e) {
            throw new StackException("Тестовый сбой");
        }


    }

    @Test
    public void checkGenericStackPartTwo(){
        assertEquals(sum, 6);
    }


}
