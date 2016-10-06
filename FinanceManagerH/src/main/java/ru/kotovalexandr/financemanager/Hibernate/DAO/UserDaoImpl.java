package ru.kotovalexandr.financemanager.Hibernate.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.kotovalexandr.financemanager.Hibernate.util.HibernateUtil;
import ru.kotovalexandr.financemanager.Model.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexkotov on 03.10.16.
 */
public final class UserDaoImpl implements IGenDao<User>{

    @Override
    public User findById(int id) {
        User user = null;
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            user = session.load(User.class, id);
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
        return user;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    public User authorisation(String login, String password){
        User user = null;
        List<User> users = new ArrayList<>();
        Session session = null;
        try{
            session = HibernateUtil.getSession();
            session.getTransaction().begin();
            users = session.createSQLQuery("select * from Users where LOGIN ='" + login + "' AND PASS = '" + hashing(password) + "'").addEntity(User.class).list();
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
        if(users.size()>0){
            user = users.get(0);
        }
        return user;
    }

    public User registration(String login, String password){
        User user = authorisation(login, password);
        if(user == null) {
            user = new User(login,hashing(password));
            CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
            commonGenDao.save(user);
        }
        return user;
    }

    public void removeUser (User user){
        CommonGenDaoImpl commonGenDao = DAOFabric.getCommonDao();
        commonGenDao.delete(user);
    }


    /**
     * Method hashing string in SHA-224 algorithm
     * @return String of hashig string
     * */
    private String hashing(String text) {
        MessageDigest md = null;
        byte[] hashingBytes = new byte[0];
        try {
            md = MessageDigest.getInstance("SHA-224");
            md.reset();
            md.update(text.getBytes());
            hashingBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bi = new BigInteger(1, hashingBytes);
        String sha_224 = bi.toString(16);
        while (sha_224.length() < 32) {
            sha_224 = "0" + sha_224;
        }
        return sha_224;
    }
}
