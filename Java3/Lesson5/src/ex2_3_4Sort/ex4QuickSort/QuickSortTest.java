package ex2_3_4Sort.ex4QuickSort;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 08.08.2016.
 */
public class QuickSortTest {

    Random rand = new Random();
    int[] arr;

    @Before
    public void setUp() throws Exception {
        int size = 50;
        arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size);
        }

    }

    @Test
    public void sort() throws Exception {
        System.out.println(Arrays.toString(arr));
        QuickSort qs = new QuickSort(arr);
        int[] myArr = qs.sort();
        System.out.println(Arrays.toString(myArr));
    }

}