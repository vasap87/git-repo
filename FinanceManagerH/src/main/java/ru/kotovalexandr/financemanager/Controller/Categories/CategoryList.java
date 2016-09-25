package ru.kotovalexandr.financemanager.Controller.Categories;

import ru.kotovalexandr.financemanager.Controller.IObservable;
import ru.kotovalexandr.financemanager.Controller.IObserver;
import ru.kotovalexandr.financemanager.View.Tools.JList.CategoryJList;

/**
 * Created by vasilenko.aleksandr on 01.08.2016.
 */
public class CategoryList implements IObservable {
    private static CategoryList instance = new CategoryList();

    private CategoryList() {
    }

    public static CategoryList getInstance() {
        return instance;
    }

    private CategoryJList categoryJList;


    @Override
    public void notifyObservers() {
        categoryJList.handelEvent();
    }

    @Override
    public void addObserver(IObserver observer) {
        categoryJList = (CategoryJList) observer;
    }
}
