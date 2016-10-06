package ru.kotovalexandr.financemanager.Controller.Services;

import org.junit.Before;
import org.junit.Test;
import ru.kotovalexandr.financemanager.Model.User;

import static org.junit.Assert.*;

/**
 * Created by alexkotov on 03.10.16.
 */
public class SignInOnServiceTest {
    private String authLogin, authPass, regLogin, regPass;
    private User authUser, regUser;
    @Before
    public void setUp() throws Exception {
        authLogin = "new User";
        authPass = "password";
        regLogin = "new reg Login";
        regPass = "new reg pass";
    }

    @Test
    public void authorisationService() throws Exception {
        authUser = SignInOnService.authorisationService(authLogin,authPass);
        assertEquals(null,authUser);
    }

    @Test
    public void registrationService() throws Exception {
        User user = SignInOnService.registrationService(regLogin, regPass);
        assertNotEquals(null, user);

    }

    @Test
    public void deleteUserService() throws Exception {
        regUser = SignInOnService.authorisationService(regLogin, regPass);
        SignInOnService.deleteUserService(regUser);
    }

}