package HardProducerConsumer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 17.08.2016.
 */
public class Main {
    public static void main(String[] args) {
        //генерим файлы и строку для поиска
        CreateFiles cf = new CreateFiles();
        File fileDir = cf.getDir();
        String searchStr = cf.getSearchStr();

        File[] files = fileDir.listFiles();
        List<File> fileList = new ArrayList<>();
        for (File file : files) {
            fileList.add(file);
        }
        while (fileList.size()!=0){
            SearchInFile searchInFile = new SearchInFile(fileList.remove(0), searchStr);
            searchInFile.search();
        }




    }
}
