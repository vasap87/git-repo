package annotation;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by admin on 05.09.2016.
 */
public class Main {

    public static void main(String[] args) {
        Car car = new Car();
        Class carClass = car.getClass();

        Method[] methods = carClass.getDeclaredMethods();
        Method initMethod = null;
        for (Method method : methods) {

            PostConstruct annot = method.getAnnotation(PostConstruct.class);
            if(annot!=null){
                initMethod = method;
            }
        }
        try {
            initMethod.invoke(car);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

}
