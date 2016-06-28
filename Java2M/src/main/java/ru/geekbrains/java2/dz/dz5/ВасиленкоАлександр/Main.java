package ru.geekbrains.java2.dz.dz5.ВасиленкоАлександр;

import java.util.Arrays;

/**
 * Created by vasilenko.aleksandr on 21.06.2016.
 */
public class Main {
    private static final int SIZE = 10000000;

    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        firstMethod(arr);
        secondMethod(arr);
    }

    private static void firstMethod(float[] arr) {
        //засекаем время
        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis() - a;
        //выводим результат
        System.out.println("Первый метод выполнялся: " + b / 1000 + "," + b % 1000 + " секунд");
    }

    private static void secondMethod(float[] arr) {
        //режем массив на 2 равные части
        //засекаем время
        long a = System.currentTimeMillis();
        float[] arr1 = new float[SIZE / 2];
        float[] arr2 = new float[SIZE / 2];
        System.arraycopy(arr, 0, arr1, 0, SIZE / 2);
        System.arraycopy(arr, SIZE / 2, arr2, 0, SIZE / 2);

        //создаём 2 объектра реализующих интерфейс Runnable
        SimpleThread sTh1 = new SimpleThread(arr1);
        SimpleThread sTh2 = new SimpleThread(arr2);

        //создаём 2 объекта класса Thread
        Thread firstThread = new Thread(sTh1);
        Thread secondThread = new Thread(sTh2);
        firstThread.setPriority(Thread.NORM_PRIORITY);
        secondThread.setPriority(Thread.NORM_PRIORITY);
        firstThread.start();
        secondThread.start();
        //ждём когда выполнятся оба потока
        long b;
        while (true) {
            if (firstThread.getState().equals(Thread.State.TERMINATED) && secondThread.getState().equals(Thread.State.TERMINATED)) {
                arr1 = sTh1.getArr();
                arr2 = sTh2.getArr();
                System.arraycopy(arr1, 0, arr, 0, SIZE / 2);
                System.arraycopy(arr2, 0, arr, SIZE / 2, SIZE / 2);
                b = System.currentTimeMillis() - a;
                break;
            }
        }
        //выводим результат
        System.out.println("Второй метод выполнялся: " + b / 1000 + "," + b % 1000 + " секунд");


    }
}
