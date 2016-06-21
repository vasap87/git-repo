package ru.vasilenkoaleksandr.myprojects.Threads;

import java.util.Arrays;

/**
 * Created by vasilenko.aleksandr on 21.06.2016.
 */
public class Main {
    private static final int SIZE = 10000000;

    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        //firstMethod(arr);
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
        float[] arr1 = new float[SIZE / 10];
        float[] arr2 = new float[SIZE / 10];
        float[] arr3 = new float[SIZE / 10];
        float[] arr4 = new float[SIZE / 10];
        float[] arr5 = new float[SIZE / 10];
        float[] arr6 = new float[SIZE / 10];
        float[] arr7 = new float[SIZE / 10];
        float[] arr8 = new float[SIZE / 10];
        float[] arr9 = new float[SIZE / 10];
        float[] arr10 = new float[SIZE / 10];

        System.arraycopy(arr, 0, arr1, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10, arr2, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 2, arr3, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 3, arr4, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 4, arr5, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 5, arr6, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 6, arr7, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 7, arr8, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 8, arr9, 0, SIZE / 10);
        System.arraycopy(arr, SIZE / 10 * 9, arr10, 0, SIZE / 10);

        //создаём 2 объектра реализующих интерфейс Runnable
        SimpleThread sTh1 = new SimpleThread(arr1);
        SimpleThread sTh2 = new SimpleThread(arr2);
        SimpleThread sTh3 = new SimpleThread(arr3);
        SimpleThread sTh4 = new SimpleThread(arr4);
        SimpleThread sTh5 = new SimpleThread(arr5);
        SimpleThread sTh6 = new SimpleThread(arr6);
        SimpleThread sTh7 = new SimpleThread(arr7);
        SimpleThread sTh8 = new SimpleThread(arr8);
        SimpleThread sTh9 = new SimpleThread(arr9);
        SimpleThread sTh10 = new SimpleThread(arr10);

        //создаём 2 объекта класса Thread
        Thread firstThread = new Thread(sTh1);
        Thread secondThread = new Thread(sTh2);
        Thread thirdThread = new Thread(sTh3);
        Thread fourthThread = new Thread(sTh4);
        Thread fifthThread = new Thread(sTh5);
        Thread sixthThread = new Thread(sTh6);
        Thread seventhThread = new Thread(sTh7);
        Thread eighthThread = new Thread(sTh8);
        Thread ninethThread = new Thread(sTh9);
        Thread tenthThread = new Thread(sTh10);
//        firstThread.setPriority(Thread.NORM_PRIORITY);
//        secondThread.setPriority(Thread.NORM_PRIORITY);
//        thirdThread.setPriority(Thread.NORM_PRIORITY);
//        fourthThread.setPriority(Thread.NORM_PRIORITY);

        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        fifthThread.start();
        sixthThread.start();
        seventhThread.start();
        eighthThread.start();
        ninethThread.start();
        tenthThread.start();
        //ждём когда выполнятся оба потока
        long b;
        while (true) {
            if (firstThread.getState().equals(Thread.State.TERMINATED)
                    && secondThread.getState().equals(Thread.State.TERMINATED)
                    && thirdThread.getState().equals(Thread.State.TERMINATED)
                    && fourthThread.getState().equals(Thread.State.TERMINATED)
                    && fifthThread.getState().equals(Thread.State.TERMINATED)
                    && sixthThread.getState().equals(Thread.State.TERMINATED)
                    && seventhThread.getState().equals(Thread.State.TERMINATED)
                    && eighthThread.getState().equals(Thread.State.TERMINATED)
                    && ninethThread.getState().equals(Thread.State.TERMINATED)
                    && tenthThread.getState().equals(Thread.State.TERMINATED)) {
                arr1 = sTh1.getArr();
                arr2 = sTh2.getArr();
                arr3 = sTh3.getArr();
                arr4 = sTh4.getArr();
                arr5 = sTh5.getArr();
                arr6 = sTh6.getArr();
                arr7 = sTh7.getArr();
                arr8 = sTh8.getArr();
                arr9 = sTh9.getArr();
                arr10 = sTh10.getArr();
                System.arraycopy(arr1, 0, arr, 0, SIZE / 10);
                System.arraycopy(arr2, 0, arr, SIZE / 10, SIZE / 10);
                System.arraycopy(arr3, 0, arr, SIZE / 10 * 2, SIZE / 10);
                System.arraycopy(arr4, 0, arr, SIZE / 10 * 3, SIZE / 10);
                System.arraycopy(arr5, 0, arr, SIZE / 10 * 4, SIZE / 10);
                System.arraycopy(arr6, 0, arr, SIZE / 10 * 5, SIZE / 10);
                System.arraycopy(arr7, 0, arr, SIZE / 10 * 6, SIZE / 10);
                System.arraycopy(arr8, 0, arr, SIZE / 10 * 7, SIZE / 10);
                System.arraycopy(arr9, 0, arr, SIZE / 10 * 8, SIZE / 10);
                System.arraycopy(arr10, 0, arr, SIZE / 10 * 9, SIZE / 10);
                b = System.currentTimeMillis() - a;
                break;
            }
        }
        //выводим результат
        System.out.println("Второй метод выполнялся: " + b / 1000 + "," + b % 1000 + " секунд");


    }
}
