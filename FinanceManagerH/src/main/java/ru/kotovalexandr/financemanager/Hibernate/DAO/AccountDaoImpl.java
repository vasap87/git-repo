package ru.kotovalexandr.financemanager.Hibernate.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.kotovalexandr.financemanager.Hibernate.util.HibernateUtil;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.Transaction;
import ru.kotovalexandr.financemanager.Model.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alexkotov on 30.09.16.
 */
public final class AccountDaoImpl implements IGenDao<Account> {


    @Override
    public Account findById(int id) {
        Account account = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            account = session.load(Account.class, id);
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
    public List<Account> getAll() {
        List<Account> accounts = new ArrayList<>();
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            accounts = session.createQuery("from Account").list();
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

    public List<Account> getAllbyUser(User user){
        List<Account> accounts = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            accounts = new ArrayList<>(session.createQuery("from Account as a where a.user.id="+user.getId()).list());
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
