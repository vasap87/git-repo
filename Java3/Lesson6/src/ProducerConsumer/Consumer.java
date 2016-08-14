package ProducerConsumer;

import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 14.08.2016.
 */
public class Consumer extends Thread {
    private BlockingQueue blockQueue;
    private String searchString;

    public Consumer(BlockingQueue blockQueue, String searchString) {
        super("CONSUMER");
        this.blockQueue = blockQueue;
        this.searchString = searchString;
        start();
    }

    @Override
    public void run() {
        //берём существующий элемент и ищем в нём подстроку

    }
}
