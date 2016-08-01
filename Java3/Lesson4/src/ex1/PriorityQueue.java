package ex1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Vasilenko on 31.07.2016.
 *
 * Class implements interface {@link IPriorityQueue}
 */
public class PriorityQueue<T extends Comparable<T>, V> implements IPriorityQueue<T, V> {

    private List<T> priorities = new ArrayList();
    private List<V> values = new ArrayList();


    @Override
    public V getMax() {
        if (priorities.size() ==0) throw new IndexOutOfBoundsException("Collection is empty");
        else{
            int index = getMaxPriorityIndex(priorities);
            return values.get(index);
        }

    }

    @Override
    public void insert(T t, V v) {
        priorities.add(t);
        values.add(v);
    }

    private int getMaxPriorityIndex(List<T> priorities) {
        T max = priorities.get(0);
        for(T t: priorities){
            if(t.compareTo(max)>0){
                max = t;
            }
        }
        return priorities.indexOf(max);
    }
}
