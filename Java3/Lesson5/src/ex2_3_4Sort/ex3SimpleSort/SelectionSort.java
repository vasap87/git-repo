package ex2_3_4Sort.ex3SimpleSort;

import ex2_3_4Sort.ISort;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public class SelectionSort implements ISort {
    private int[] arr;

    public SelectionSort(int[] arr){
        this();
        this.arr=arr;
    }

    public SelectionSort(){

    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int[] sort() {
        int maxElementIndex = 0;
        int maxLength = arr.length - 1;
        int maxElement = arr[maxElementIndex];
        while (maxLength!=0) {
            for (int i = 0; i <= maxLength; i++) {
                if (arr[i] > maxElement) {
                    maxElement = arr[i];
                    maxElementIndex = i;
                }
            }
            swap(maxLength, maxElementIndex);
            maxLength--;
            maxElement = 0;
        }
        return arr;
    }

    private void swap(int maxIndex, int maxElementIndex) {
        int temp = arr[maxIndex];
        arr[maxIndex] = arr[maxElementIndex];
        arr[maxElementIndex] = temp;
    }
}
