package reflection;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 02.09.2016.
 */
public class FlatParser {
    private List<Flat> flatList;

    public FlatParser() {
        flatList = new ArrayList<>();
    }

    public void parse(File toParse) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //не проверяем на соответствие xsd
        factory.setValidating(false);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document root = builder.parse(toParse);
        parseChild(root);
    }

    private void parseChild(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node child = childNodes.item(i);
            if (child.getNodeName().equals("flat")) {
                parseFlat(child);
            } else {
                parseChild(child);
            }
        }
    }

    private void parseFlat(Node flat) {
        Flat flatObj = new Flat();
        Class flatClass = flatObj.getClass();
        Method[] methods = flatClass.getDeclaredMethods();

        NodeList flatParams = flat.getChildNodes();
        for (int i = 0; i < flatParams.getLength(); i++) {          //для каждого параметра
            Node param = flatParams.item(i);
            if (param.getNodeName().equals("property")) {
                String propName = null;
                String propVal = null;
                NamedNodeMap attrs = param.getAttributes();         //смотрим атрибуты
                for (int j = 0; j < attrs.getLength(); j++) {
                    Attr name = (Attr) attrs.item(j);
                    if(name.getName().equalsIgnoreCase("name")){
                        propName = name.getValue();
                    }
                    if(name.getName().equalsIgnoreCase("val")){
                        propVal = name.getValue();
                    }
                }
                // TODO: 02.09.2016 вызвать нужный метод с нужным значением:
                for (Method m : methods) {
                    String methodName = m.getName();
                    if (methodName.equalsIgnoreCase("set"+propName)){
                        Class[] methodParamTypes = m.getParameterTypes();
                        Object[] methodParamValues = new Object[] {new Integer(propVal)};
                        try {
                            Method method = flatClass.getDeclaredMethod(methodName,methodParamTypes);
                            method.setAccessible(true);
                            method.invoke(flatObj, methodParamValues);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        flatList.add(flatObj);
    }

    public List<Flat> getFlatList() {
        return flatList;
    }
}
