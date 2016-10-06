package ru.kotovalexandr.financemanager.Hibernate.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.kotovalexandr.financemanager.Hibernate.util.HibernateUtil;
import ru.kotovalexandr.financemanager.Model.Transaction;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexkotov on 30.09.16.
 */
public final class TransactionDaoImpl implements IGenDao<Transaction> {


    @Override
    public Transaction findById(int id) {
        Transaction account = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            account = session.load(Transaction.class, id);
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
    public List<Transaction> getAll() {
        List<Transaction> accounts = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            accounts = new ArrayList<>(session.createQuery("from Transaction").list());
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
