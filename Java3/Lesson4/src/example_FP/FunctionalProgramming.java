package example_FP;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 31.07.2016.
 */

public class FunctionalProgramming {

    // Принимает одно значение, возвращает одно значение
    interface Function<R, T> {
        R apply(T t);
    }

    // Принимает элемент и проверяет его на условие
    interface Predicate<T> {
        boolean test(T t);
    }

    // Принимает 2 аргумента, проводит опреацию и возвращает результат
    interface BiFunction<R, U, V> {
        R apply(U u, V v);
    }

    // 2 аргумента одного типа
    interface BiOperator<T> extends BiFunction<T, T, T> {
        T apply(T t, T tt);
    }

    static class Square implements Function<Integer, Integer> {
        public Integer apply(Integer val) {
            return val * val;
        }
    }

    /*
    Применить к каждому элементу коллекции заданную операцию
     */
    static <R, T> List<R> map(Collection<T> collection, Function<R, T> functor) {
        List<R> result = new ArrayList<>();
        for (T t : collection) {
            result.add(functor.apply(t));
        }
        return result;
    }

    /*
    Проверить элементы коллекции на заданное условие.
    Вернуть коллекцию элементов, прошедших фильтр
     */
    static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /*
    Последовательно применить операцию ко всем элементам коллекции
    Вернуть одно значение
     */
    static <T> T reduce(List<T> list, T init, BiOperator<T> op) {
        for (T t : list) {
            init = op.apply(init, t);
        }
        return init;
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        System.out.println("map [^2]: " + numbers + " -> " + map(numbers, new Square()));

        System.out.println("filter [%3]: " + numbers + " -> " + filter(numbers, new Predicate<Integer>() {
            @Override
            public boolean test(Integer val) {
                return val % 3 != 0;
            }
        }));

        // Lambda!
        System.out.println("Lambda filter [!%3]: " + numbers + " -> " + filter(numbers, (p) -> p % 3 != 0));


        System.out.println("reduce [+]: " + numbers + " -> " + reduce(numbers, 0, new BiOperator<Integer>() {
            @Override
            public Integer apply(Integer t, Integer tt) {
                return t + tt;
            }
        }));

        System.out.println("Lambda reduce [*]: " + numbers + " -> " + reduce(numbers, 1, (t, tt) -> t * tt));

        List<String> strings = Arrays.asList("1", "2", "3");
        System.out.println("Concat: " + strings + " -> " + reduce(strings, "", (t, tt) -> t + tt));
    }
}

