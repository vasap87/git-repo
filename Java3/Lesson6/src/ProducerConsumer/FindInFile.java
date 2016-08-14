package ProducerConsumer;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 14.08.2016.
 */
public class FindInFile {
    private final static File FILE = new File("text.file");
    private final static String SEARCH_STRING = "Inter Process Communication (IPC)";

    public static void main(String[] args) {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(10);
        Producer producer = new Producer(FILE, blockQueue);
        Consumer consumer = new Consumer(blockQueue, SEARCH_STRING);
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
