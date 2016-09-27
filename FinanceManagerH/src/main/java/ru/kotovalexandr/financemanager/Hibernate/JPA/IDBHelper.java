package ru.kotovalexandr.financemanager.Hibernate.JPA;

import java.util.List;

/**
 * Created by alexkotov on 26.09.16.
 *
 * This interface to implement Class which work with databases.
 */
public interface IDBHelper<T> {
    /**
     * This method help to save Entity in database
     * */
    void save(T t);

    /**
     * This method help to update Entity in database
     * */
    void update (T t);

    /**
     * This method help to delete Entity from database
     * */
    void delete (T t);

    /**
     * This method help to get Entity from database by ID
     * */
    T getByID (int id);

    /**
     * This method help to get all Entity from database
     * */
    List<T> getAll();


}
