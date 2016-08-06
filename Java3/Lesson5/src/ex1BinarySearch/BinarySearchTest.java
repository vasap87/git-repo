package ex1BinarySearch;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public class BinarySearchTest {
    @Test
    public void search() throws Exception {
        BinarySearch<Integer> integerSearch = new BinarySearch<>();
        Integer[] ints = {-9,-7,-7,-3,-1,0,12,13,17};
        assertEquals(integerSearch.search(ints, 20), -1);
        assertEquals(integerSearch.search(null, 0), -1);
        assertEquals(integerSearch.search(ints, -9), 0);
        assertEquals(integerSearch.search(ints, 0), 5);
        assertEquals(integerSearch.search(ints, -10), -1);

        BinarySearch<String> stringSearch = new BinarySearch<>();
        String[] strings = {"ab","af","ba","bt","fs","ks","zs"};
        assertEquals(stringSearch.search(strings,"aa"), -1);
        assertEquals(stringSearch.search(strings,"ba"), 2);
        assertEquals(stringSearch.search(strings,"bb"), -1);
        assertEquals(stringSearch.search(strings,"zz"), -1);
        assertEquals(stringSearch.search(strings,"zs"), strings.length-1);

    }

}