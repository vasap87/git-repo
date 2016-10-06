package ru.kotovalexandr.financemanager.Hibernate.DAO;


/**
 * Created by alexkotov on 30.09.16.
 */
public final class DAOFabric {

    private static AccountDaoImpl accountDao;
    private static CategoryDaoImpl categoryDao;
    private static TransactionDaoImpl transactionDao;
    private static UserDaoImpl userDao;
    private static CommonGenDaoImpl commonDao;

    public static AccountDaoImpl getAccountDao(){
        if (accountDao == null){
            accountDao = new AccountDaoImpl();
        }
        return accountDao;
    }

    public static CategoryDaoImpl getCategoryDao(){
        if(categoryDao == null){
            categoryDao = new CategoryDaoImpl();
        }
        return categoryDao;
    }

    public static TransactionDaoImpl getTransactionDao(){
        if(transactionDao == null){
            transactionDao = new TransactionDaoImpl();
        }
        return transactionDao;
    }


    public static CommonGenDaoImpl getCommonDao(){
        if(commonDao == null){
            commonDao = new CommonGenDaoImpl();
        }
        return commonDao;
    }

    public static UserDaoImpl getUserDao(){
        if(userDao == null){
            userDao = new UserDaoImpl();
        }
        return userDao;
    }
}
