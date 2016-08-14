package ProducerConsumer;

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

    public Producer(File file, BlockingQueue blockQueue){
        super("PRODUCER");
        this.blockQueue = blockQueue;
        this.file = file;
        start();
    }

    @Override
    public void run() {
        //читаем из файла 4кб данных и пишем в BlockingQueue
        try(FileInputStream fis = new FileInputStream(file)) {
            byte[] arr = new byte[4096];
            while(fis.read(arr)!=-1);
            blockQueue.put(new String(arr));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
