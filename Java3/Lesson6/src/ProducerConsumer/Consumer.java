package ProducerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 14.08.2016.
 */
public class Consumer extends Thread {
    private BlockingQueue blockQueue;
    private String searchString;
    private Thread thread;
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    public Consumer(BlockingQueue blockQueue, String searchString, Thread thread) {
        super("CONSUMER");
        this.blockQueue = blockQueue;
        this.searchString = searchString;
        this.thread = thread;
//        start();
    }

    @Override
    public void run() {
        //берём существующий элемент и ищем в нём подстроку
        try {
            boolean isFound = false;
            StringBuilder sb;
            sleep(10); //ждём, чтобы продюссер начал складывать в очередь куски
            while(blockQueue.size()>0){
                sb = new StringBuilder((String) blockQueue.take());
                log.info("CONSUMER забрал из очереди");
                if(sb.indexOf(searchString)!=-1) {
                    isFound = true;
                    break;
                }
            }
            thread.interrupt();
            log.info("поток "+ this.getName() + " прерывает поток " + thread.getName());
            System.out.println("Фраза: \""+searchString+"\" " + (isFound?"найдена":"не найдена"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("CONSUMER завершил работу");
    }
}
