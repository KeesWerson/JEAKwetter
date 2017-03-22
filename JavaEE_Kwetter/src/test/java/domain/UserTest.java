package domain;

import DAO.UserDAO;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.junit.Assert.*;

/**
 * Created by Kees on 07/03/2017.
 */
public class UserTest {

    @Inject
    UserDAO userDAO;

    @Inject
    EntityManager entityManager;

    public User user;

    public UserTest() {
        this.user = new User();
    }


    @Test
    public void testConcatenate() {
        User user2 = new User("Kees", "Werson");

        assertTrue(user2.getTrue());
    }

    /*@Test
    public void testSaveUser() {
        user = new User("Kees", "Werson");


        userDAO.saveUser(user);

        assertNotNull(user.getId());
    }*/
}