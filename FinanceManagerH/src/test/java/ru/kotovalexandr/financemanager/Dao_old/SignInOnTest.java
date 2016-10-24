package ru.kotovalexandr.financemanager.Dao_old;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Created by admin on 24.08.2016.
 */
public class SignInOnTest {
    @Before
    public void setUp() throws Exception {
        SignInOn.getInstance().registration("user1", "pas1");
    }


    @Test
    public void authorisationTest() throws Exception {
        int newLoginID = SignInOn.getInstance().authorisation("user1", "pas1");
        assertNotEquals(0, newLoginID);
    }

    @Test
    public void doubleRegistrationTest() throws Exception {
        int authLoginID = SignInOn.getInstance().registration("user1", "pas1");
        assertEquals(0, authLoginID);
    }

}