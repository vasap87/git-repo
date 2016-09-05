package reflection;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 04.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        File toPars = new File("toParse.xml");
        FlatParser parser = new FlatParser();
        try {
            parser.parse(toPars);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for (Flat flat : parser.getFlatList()) {
            System.out.println(flat);
        }

    }
}
