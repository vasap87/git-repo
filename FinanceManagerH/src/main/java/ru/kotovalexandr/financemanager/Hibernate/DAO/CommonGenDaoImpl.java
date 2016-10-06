package ru.kotovalexandr.financemanager.Hibernate.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.kotovalexandr.financemanager.Hibernate.util.HibernateUtil;


/**
 * Created by alexkotov on 30.09.16.
 */
public final class CommonGenDaoImpl<T> implements ICommonGenDao<T> {
    @Override
    public void save(T entity) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.save(entity);
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void delete(T entity) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }

    @Override
    public void update(T entity) {
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            session.update(entity);
            session.getTransaction().commit();
        } catch (HibernateException e){
            e.printStackTrace();
            if(session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
    }

}
