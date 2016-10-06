package ru.kotovalexandr.financemanager.Hibernate.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.kotovalexandr.financemanager.Hibernate.util.HibernateUtil;
import ru.kotovalexandr.financemanager.Model.Category;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexkotov on 30.09.16.
 */
public final class CategoryDaoImpl implements IGenDao<Category> {


    @Override
    public Category findById(int id) {
        Category account = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            account = session.load(Category.class, id);
            session.getTransaction().commit();
        } catch (HibernateException e){
            if(session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return account;
    }

    @Override
    public List<Category> getAll() {
        List<Category> accounts = new ArrayList();
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            accounts = session.createQuery("from CATEGORIES").list();
            session.getTransaction().commit();
        } catch (HibernateException e){
            if(session!=null){
                session.getTransaction().rollback();
            }
        } finally {
            if(session!=null){
                session.close();
            }
        }
        return accounts;
    }
}
