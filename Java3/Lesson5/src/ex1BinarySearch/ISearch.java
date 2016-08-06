package ex1BinarySearch;

/**
 * Created by vasilenko.aleksandr on 05.08.2016.
 */
public interface ISearch<T extends Comparable<T>> {
    int search(T[] arr, T t);
}
