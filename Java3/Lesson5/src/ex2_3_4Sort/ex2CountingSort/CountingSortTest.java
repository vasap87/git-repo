package ex2_3_4Sort.ex2CountingSort;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public class CountingSortTest {
    @Test
    public void sort() throws Exception {

        Random rand = new Random();
        int nums = 100;
        int min = 50, max = 50;
        int[] arr = new int[nums];
        for (int i = 0; i < nums; i++) {
            int insertValue = rand.nextInt(100);
            if(insertValue>max) max= insertValue;
            if(insertValue<min) min = insertValue;
            arr[i] = insertValue;
        }
        CountingSort cs = new CountingSort(arr);
        arr = cs.sort();
        assertEquals(arr[0], min);
        assertEquals(arr[nums-1], max);
    }

}