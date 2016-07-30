package ex2;

import org.junit.Test;

import java.util.Hashtable;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 25.07.2016.
 * Test for exercise 2
 */
public class AlgorithmTest {

    @Test
    public void findMaxElementInArrayTest(){
        double arr[] = {-2,3,4,-5,-1,4,-12,432,-12,23124,-4};
        double max = Algorithm.findMaxElementInArray(arr);
        assertEquals(max, 23124, 0);
    }

    @Test
    public void reverseStringTest(){
        String s = Algorithm.reverseString("1q2w3e4r");
        assertEquals("r4e3w2q1", s);
    }

    @Test
    public void countLettersTest(){
        String s = "asdasdasda";
        Map<String, Integer> value = new Hashtable<>();
        value.put("a", 4);
        value.put("s", 3);
        value.put("d", 3);
        assertEquals(Algorithm.countLetters(s), value);
    }



}