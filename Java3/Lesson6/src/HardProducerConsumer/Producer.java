package HardProducerConsumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class Producer extends Thread {

    private File file;
    private ConcurrentLinkedQueue partsOfFile;
    private Logger log = LoggerFactory.getLogger(Producer.class);

    private static int count = 0;

    public Producer(File file, ConcurrentLinkedQueue<String> partsOfFile) {
        count++;
        setName("PRODUCER "+count);
        this.file = file;
        this.partsOfFile = partsOfFile;
    }

    @Override
    public void run() {
        log.info("поток "+ getName() + " запустился");
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] buff = new byte[4096];
            while(fis.read(buff)!=-1){
                partsOfFile.add(new String(buff));
                buff = new byte[4096];
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("поток "+ getName() + " отработал");


    }
}
