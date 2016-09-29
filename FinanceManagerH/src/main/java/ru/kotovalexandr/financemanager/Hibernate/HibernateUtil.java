package ru.kotovalexandr.financemanager.Hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by alexkotov on 29.09.16.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {

        Configuration configuration = new Configuration()
                .addClass(ru.kotovalexandr.financemanager.Model.Account.class)
                .addClass(ru.kotovalexandr.financemanager.Model.Transaction.class)
                .addClass(ru.kotovalexandr.financemanager.Model.Category.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.hbm2ddl.auto", "update")
                .setProperty("hibernate.connection.url", "jdbc:h2:finance");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .buildServiceRegistry();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    }

    public static Session getSession() throws HibernateException{
        return sessionFactory.openSession();
    }


}
