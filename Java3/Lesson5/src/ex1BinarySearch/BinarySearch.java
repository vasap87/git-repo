package ex1BinarySearch;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 * Search index of element in sorted array;
 */
public class BinarySearch<T extends Comparable<T>> implements ISearch<T> {

    @Override
    public int search(T[] arr, T t) {
        int index = -1;
        if (arr != null) {
            int from = 0;
            int to = arr.length;
            int mid;
            while (from < to){
                 mid = (from + to) / 2;
                if(arr[mid].compareTo(t)==0){
                    return mid;
                }
                if(arr[mid].compareTo(t)>0){
                    to = mid;
                }else{
                    from = mid+1;
                }
            }
        }
        return index;
    }
}
