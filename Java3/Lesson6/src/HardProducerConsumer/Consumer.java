package HardProducerConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class Consumer extends Thread {

    private ConcurrentLinkedQueue<String> partsOfFile;
    private String searchStr;
    private SearchInFile searchInFile;
    private Logger log = LoggerFactory.getLogger(Consumer.class);

    private static int count = 0;

    public Consumer(ConcurrentLinkedQueue<String> partsOfFile, String searchStr, SearchInFile searchInFile) {
        count++;
        setName("CONSUMER "+count);
        this.partsOfFile = partsOfFile;
        this.searchStr = searchStr;
        this.searchInFile = searchInFile;
    }

    @Override
    public void run() {
        log.info("поток "+ getName() + " запустился");
        while(partsOfFile.size()!=0){
            String s = partsOfFile.poll();
            if(s.indexOf(searchStr)!=-1){
                searchInFile.searchComplete("подстрока найдена");
//                searchInFile.closeThreads();
                break;
            }
        }
        log.info("поток "+ getName() + " отработал");
    }
}
