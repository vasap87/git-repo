package ex2_3_4Sort.ex3SimpleSort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public class SelectionSortTest {
    @Test
    public void sort() throws Exception {
        Random rand = new Random();
        int max =25, min=25;
        int size = 50;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            int inputValue = rand.nextInt(size);
            if(inputValue>max) max=inputValue;
            if(inputValue<min) min=inputValue;
            arr[i] = inputValue;
        }

        System.out.println(Arrays.toString(arr));
        SelectionSort ss = new SelectionSort(arr);
        int[] mySort = ss.sort();
        System.out.println(Arrays.toString(mySort));
        Arrays.sort(arr);

        assertEquals(mySort, arr);
        assertEquals(arr[size-1], max);
    }

}