package ru.gb.AlexVasilenko.java3.lesson1.ex1.test;

import org.testng.annotations.Test;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.GenericStack;
import ru.gb.AlexVasilenko.java3.lesson1.ex1.StackException;

import static org.testng.Assert.assertEquals;

/**
 * Created by admin on 10.07.2016.
 */
public class GenericStackTest {

    private int sum = 0;

    @Test
    public void checkGenericStackPartOne() {
        GenericStack<Integer> intStack = new GenericStack<>(3);
        try {
            intStack.push(1);
            intStack.push(2);
            intStack.push(3);
        } catch (StackException e) {
            e.printStackTrace();
        }
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
        } catch (StackException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkGenericStackPartTwo(){
        assertEquals(sum, 6);
    }


}
