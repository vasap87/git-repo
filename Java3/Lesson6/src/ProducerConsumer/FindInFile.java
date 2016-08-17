package ProducerConsumer;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 14.08.2016.
 */
public class FindInFile {
    private final static File FILE = new File("text.file");
//    private final static String SEARCH_STRING = "abracadabra";
//    private final static String SEARCH_STRING = "Inter Process Communication (IPC)";
    private final static String SEARCH_STRING = "this text you have to search";

    public static void main(String[] args) {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(1 ,true);
        Producer producer = new Producer(FILE, blockQueue);
        Consumer consumer = new Consumer(blockQueue, SEARCH_STRING, producer);
        long start = System.nanoTime();
        producer.start();
        consumer.start();
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long finish = System.nanoTime()-start;
        System.out.println("На поиск ушло: "+ (finish/1000000)+","+ (finish%1000000)+"ms");
    }
}
