package annotation;

import javax.annotation.PostConstruct;

/**
 * Created by vasilenko.aleksandr on 08.09.2016.
 */
public class Car {
    int wheels;

    void setWheels(int wheels){
        this.wheels = wheels;
    }

    @PostConstruct
    void drive(){
        System.out.println("Car is drive");
    }

}
