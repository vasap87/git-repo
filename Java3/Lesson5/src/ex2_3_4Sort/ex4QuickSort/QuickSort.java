package ex2_3_4Sort.ex4QuickSort;

import ex2_3_4Sort.ISort;
import ex2_3_4Sort.ex3SimpleSort.SelectionSort;

/**
 * Created by vasilenko.aleksandr on 08.08.2016.
 */
public class QuickSort implements ISort {

    private int[] arr;

    public QuickSort(int[] arr) {
        this.arr = arr;
    }

    @Override
    public int[] sort() {
        //если длинна массива меньше 30 элементов, то разумнее выполнить SelectionSort
        SelectionSort ss = new SelectionSort();
        if (arr.length < 30) {
            ss.setArr(arr);
            arr = ss.sort();
        } else {
            quickSort(arr, 0, arr.length - 1);
        }
        return arr;
    }

    private void quickSort(int[] arr, int i, int j) {
        int from = i;
        int to = j;
        int mid = i - (i - j) / 2;
        while (from < to) {
            while (from < mid && arr[from] <= arr[mid]) from++;
            while (to > mid && arr[to] >= arr[mid]) to--;
            if (from < to) {
                swap(from, to);
                if (from == mid) mid = to;
                else if (to == mid) mid = from;
            }
        }
        if ((mid - i) > 30) quickSort(arr, i, mid);
        if ((j - mid) > 30) quickSort(arr, mid + 1, j);
    }

    private void swap(int from, int to) {
        int temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;
    }
}
