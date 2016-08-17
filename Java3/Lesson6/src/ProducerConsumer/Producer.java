package ProducerConsumer;

import org.slf4j.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by admin on 14.08.2016.
 */
public class Producer extends Thread {
    private BlockingQueue blockQueue;
    private File file;
    private Logger log = LoggerFactory.getLogger(Producer.class);

    public Producer(File file, BlockingQueue blockQueue){
        super("PRODUCER");
        this.blockQueue = blockQueue;
        this.file = file;
//        start();
    }

    @Override
    public void run() {
        //читаем из файла 2кб данных и пишем в BlockingQueue
        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] arr = new byte[2048];
            while(fis.read(arr)!=-1){
                if(isInterrupted()) break;
                log.info("PRODUCER положил в очередь");
                blockQueue.put(new String(arr));
                arr = new byte[2048];
            }
        } catch (FileNotFoundException e) {
            log.error("файл не найден");
        } catch (InterruptedException e) {
            log.error("поток "+ this.getName()+ " прерван");
        }catch (IOException e) {
            log.error("Иные исключения");
        }
        log.info("PRODUCER завершил работу");
    }
}
