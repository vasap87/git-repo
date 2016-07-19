package ru.gb.alexvasilenko.java3.lesson2.fix.dao;

import java.util.List;

/**
 * Created by Vladislav Gasanov on 13.07.2016.
 */
public interface IGenericDao<T> {
    void save(T man);
    void remove(T man);
    void update(T man);
    T findById(int id);
    List<T> getAll();
}
