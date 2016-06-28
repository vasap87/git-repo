package ru.geekbrains.java2.dz.dz5.ВасиленкоАлександр;

/**
 * Created by vasilenko.aleksandr on 21.06.2016.
 */
public class SimpleThread implements Runnable {
    private float[] arr;

    public SimpleThread(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i]*Math.sin(0.2f +i/5)*Math.cos(0.2f +i/5)*Math.cos(0.4f +i/2));
        }
    }

    public float[] getArr() {
        return arr;
    }
}
