package HardProducerConsumer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class CreateFiles {

    //size of file in byte
    public static final int FILE_SIZE = 10000000;
    public static final int FILE_COUNT = 10;
    //length of search string
    public static final int SEARCH_LETTER_COUNT = 2;

    private File dir;
    private Random rand = new Random();

    public CreateFiles() {

        try {
            File dir = new File("search");
            dir.mkdir();
            this.dir = dir;
            for (int i = 0; i < FILE_COUNT; i++) {
                File f = new File(dir, i + ".txt");
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                byte[] writebuff = new byte[FILE_SIZE];
                for (int j = 0; j < writebuff.length; j++) {
                    writebuff[j] = (byte) (rand.nextInt(225) - 127);
                }
                fos.write(writebuff);
                fos.flush();
                fos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDir() {
        return dir;
    }

    public String getSearchStr() {
        byte[] byteBuff = new byte[SEARCH_LETTER_COUNT];
        for (int i = 0; i < byteBuff.length; i++) {
            byteBuff[i] = (byte) (rand.nextInt(255) - 127);
        }
        return new String(byteBuff);
    }
}
