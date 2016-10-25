package ru.kotovalexandr.financemanager.Controller.Services;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(TotalAmountService.class);

    public static BigDecimal getTotalAmount(User user) {
        BigDecimal result = new BigDecimal(0);
        AccountDaoImpl accountDao = DAOFabric.getAccountDao();
        List<Account> accounts = accountDao.getAllbyUser(user);
        for (Account account :accounts) {
            account.updateAmmount();
            logger.info("Account with number: "+ account.getNumber() + " has " + account.getAmount().toString());
            result = result.add(account.getAmount());
        }
        logger.info("Total amount is: "+ result.toString());
        return result.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
}
