package ex1;

/**
 * Created by admin on 30.07.2016.
 */
public interface IPriorityQueue<K extends Comparable<K>, T> {
    T getMax();
    void insert(K k, T t);
}
