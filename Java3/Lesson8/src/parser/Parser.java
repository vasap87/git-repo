package parser;

import org.w3c.dom.*;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by vasilenko.aleksandr on 30.08.2016.
 */
public class Parser {
    static StringBuilder sb = new StringBuilder();
    static char pref = '\t';

    public static void main(String[] args) {
        File toParse = new File("toParse.xml");
        try {
            parse(toParse);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(sb);
    }

    private static void parse(File toParse) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //не проверяем на соответствие xsd
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document root = builder.parse(toParse);
        getChildNodes(root, 0);

    }

    private static void getChildNodes(Node node, int level) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child instanceof Element) {
                printNode(child, level);
                getChildNodes(child, level + 1);
            }
        }
    }

    private static void printNode(Node node, int level) {
        for (int i = 0; i < level; i++) {
            sb.append(pref);
        }
        sb.append("node: " + node.getNodeName());
        if(node.hasAttributes()) {
            NamedNodeMap attrs = node.getAttributes();
            for (int i = 0; i < attrs.getLength(); i++) {
                Attr attr = (Attr) attrs.item(i);
                sb.append(" ["+attr.getName()+"="+attr.getValue()+"]");
            }
        }
        sb.append('\n');
    }
}
