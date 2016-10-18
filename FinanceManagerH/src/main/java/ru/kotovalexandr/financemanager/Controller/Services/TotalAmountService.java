package ru.kotovalexandr.financemanager.Controller.Services;



import ru.kotovalexandr.financemanager.Hibernate.DAO.AccountDaoImpl;
import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Model.Account;
import ru.kotovalexandr.financemanager.Model.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by vasilenko.aleksandr on 01.08.2016.
 */
public class TotalAmountService {
     private TotalAmountService(){}

    public static BigDecimal getTotalAmount(User user) {
        BigDecimal result = new BigDecimal(0);
        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);
        for (Account account :accounts) {
            account.updateAmmount();
            result.add(account.getAmount());
        }
        return result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
