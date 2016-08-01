package ex1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 31.07.2016.
 */
public class PriorityQueueTest {
    PriorityQueue<Integer, String> testPriorityQueue;

    @Before
    public void setUp() throws Exception {
        testPriorityQueue = new PriorityQueue<>();
        testPriorityQueue.insert(6,"six");
        testPriorityQueue.insert(5,"five");
        testPriorityQueue.insert(1,"one");
        testPriorityQueue.insert(2,"two");
        testPriorityQueue.insert(3,"three");
    }

    @Test
    public void getMaxTest() throws Exception {
        assertEquals("six",testPriorityQueue.getMax());
    }

    @Test
    public void insertTest() throws Exception {
        testPriorityQueue.insert(9,"nine");
        assertEquals("nine", testPriorityQueue.getMax());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void priorityQueueExeption(){
        new PriorityQueue<>().getMax();
    }


}