package ex2_3_4Sort.ex2CountingSort;

import ex2_3_4Sort.ISort;

import java.util.Arrays;


/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public class CountingSort implements ISort {

    int [] arr;

    public CountingSort(int [] arr){
        this.arr=arr;
    }

    @Override
    public int[] sort() {
        int maxInt = findMaxInt(arr);

        int[] nums = new int[maxInt+1];
        int[] counts = new int[maxInt+1];
        Arrays.fill(counts,0);
        for (int i = 0; i < arr.length; i++) {
            nums[arr[i]] = arr[i];
        }
        for (int i = 0; i < nums.length; i++) {
            for(int x : arr){
                if(i == x) counts[i]++;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j< counts.length; j++){
                while(counts[j]>0){
                    arr[i] = j;
                    i++;
                    counts[j]--;
                }
            }
        }

        return arr;
    }

    private int findMaxInt(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max) max=arr[i];
        }
        return max;
    }
}
