package ru.kotovalexandr.financemanager.Hibernate.DAO;

import java.util.List;

/**
 * Created by alexkotov on 30.09.16.
 */
public interface IGenDao<T> {
    T findById(int id);

    List<T> getAll();}
