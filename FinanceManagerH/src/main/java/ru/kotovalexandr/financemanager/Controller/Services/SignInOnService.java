package ru.kotovalexandr.financemanager.Controller.Services;

import ru.kotovalexandr.financemanager.Hibernate.DAO.DAOFabric;
import ru.kotovalexandr.financemanager.Hibernate.DAO.UserDaoImpl;
import ru.kotovalexandr.financemanager.Model.User;


/**
 * Created by alexkotov on 03.10.16.
 */
public final class SignInOnService {
    private SignInOnService(){

    }

    public static User authorisationService (String login, String password){
        UserDaoImpl userDao = DAOFabric.getUserDao();
        User user = userDao.authorisation(login, password);
        return user;

    }

    public static User registrationService(String login, String password){
        UserDaoImpl userDao = DAOFabric.getUserDao();
        User user = userDao.registration(login, password);
        return user;
    }

    public static void deleteUserService (User user){
        UserDaoImpl userDao = DAOFabric.getUserDao();
        userDao.removeUser(user);
    }
}
