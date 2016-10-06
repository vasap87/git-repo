package ru.kotovalexandr.financemanager.Hibernate.DAO;


/**
 * Created by Alex Kotov on 30.09.2016.
 * This interface identifies components
 * that can be used to connect to different databases.
 */
public interface ICommonGenDao<T> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);



}
