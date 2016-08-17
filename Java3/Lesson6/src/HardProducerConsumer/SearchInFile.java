package HardProducerConsumer;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class SearchInFile {
    private File file;
    private String searchStr;
    private ExecutorService consumerService;
    private Producer producer;

    public SearchInFile(File file, String searchStr) {
        this.file = file;
        this.searchStr = searchStr;
    }

    public void search(){
        ConcurrentLinkedQueue<String> partsOfFile = new ConcurrentLinkedQueue<>();
        Producer producer = new Producer(file, partsOfFile);
        producer.start();
        ExecutorService consumerService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Consumer consumer = new Consumer(partsOfFile, searchStr, this);
            consumerService.execute(consumer);
        }

    }

    public void searchComplete(String message){
        System.out.println("поиск в файле "+ file.getName()+ " "+ message);
    }

//    public void closeThreads(){
//        consumerService.shutdown();
//    }
}
