package ru.kotovalexandr.financemanager.Controller.Transaction;


import ru.kotovalexandr.financemanager.Controller.IObservable;
import ru.kotovalexandr.financemanager.Controller.IObserver;
import ru.kotovalexandr.financemanager.View.Tools.JList.TransactionJList;

/**
 * Created by vasilenko.aleksandr on 26.07.2016.
 */
public class TransactionList implements IObservable {

    private TransactionJList transactionJList;

    private static TransactionList instance = new TransactionList();

    public static TransactionList getInstance() {
        return instance;
    }

    private TransactionList() {
    }

    @Override
    public void notifyObservers() {
        transactionJList.handelEvent();
    }

    @Override
    public void addObserver(IObserver observer) {
        transactionJList = (TransactionJList) observer;
    }
}
