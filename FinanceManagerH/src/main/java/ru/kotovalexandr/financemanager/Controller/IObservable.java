package ru.kotovalexandr.financemanager.Controller;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 */
public interface IObservable {
    void notifyObservers();
    void addObserver(IObserver observer);
}
