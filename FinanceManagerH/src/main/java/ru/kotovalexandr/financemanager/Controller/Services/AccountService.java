package ru.kotovalexandr.financemanager.Controller.Services;


import ru.kotovalexandr.financemanager.Hibernate.DAO.AccountDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.CommonGenDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.User;
import ru.kotovalexandr.financemanager.View.Tools.JList.AccountJList;

import java.util.List;

/**
 * Created by Alexandr Kotov on 24.08.2016.
 * This class is service to give data in view.
 */
public final class AccountService {

    private AccountService() {
    }

    public static void updateList(AccountJList accountJList, User user) {

        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);
        for(Account account: accounts){
            account.updateAmmount();
        }
        Object arr[] = accounts.toArray();
        accountJList.setListData(arr);

    }

    public static void remove(Account account) {
        CommonGenDaoImpl commonDao = DAOFabric.getCommonDao();
        commonDao.delete(account);
    }

    public static void addOrUpdateAccount(Account account, int operID) {
        CommonGenDaoImpl commonDao = DAOFabric.getCommonDao();
        switch (operID) {
            case 0: {
                commonDao.save(account);
                break;
            }
            case 1: {
                commonDao.update(account);
                break;
            }
        }
    }


}
