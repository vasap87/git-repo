package HardProducerConsumer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class SearchInFile {
    private File file;
    private String searchStr;
    private List<Consumer> myConsumerService;
    private Producer producer;

    public SearchInFile(File file, String searchStr) {
        this.file = file;
        this.searchStr = searchStr;
    }

    public void search(){
        ConcurrentLinkedQueue<String> partsOfFile = new ConcurrentLinkedQueue<>();
        producer = new Producer(file, partsOfFile);
        producer.start();
        myConsumerService = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Consumer consumer = new Consumer(partsOfFile, searchStr, this);
            myConsumerService.add(consumer);
        }

    }

    public void searchComplete(String message){
        System.out.println("поиск в файле "+ file.getName()+ " "+ message);
    }

    public void closeThreads(){
        if(producer.isAlive()){
            producer.interrupt();
        }
        for (Consumer consumer: myConsumerService) {
            if(consumer.isAlive()) consumer.interrupt();
        }
    }
}
