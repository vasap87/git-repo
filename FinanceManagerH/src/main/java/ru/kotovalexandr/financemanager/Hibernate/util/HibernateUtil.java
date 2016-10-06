package ru.kotovalexandr.financemanager.Hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by alexkotov on 29.09.16.
 */
public final class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(ru.kotovalexandr.financemanager.Model.Account.class)
                .addAnnotatedClass(ru.kotovalexandr.financemanager.Model.Transaction.class)
                .addAnnotatedClass(ru.kotovalexandr.financemanager.Model.Category.class)
                .addAnnotatedClass(ru.kotovalexandr.financemanager.Model.User.class)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "")
                .setProperty("hibernate.enable_lazy_load_no_trans", "true")
                .setProperty("hibernate.connection.url", "jdbc:h2:~/mine/java-projects/git-repo/FinanceManagerH/financeH2;MV_STORE=FALSE")
                .setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        sessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }


}
