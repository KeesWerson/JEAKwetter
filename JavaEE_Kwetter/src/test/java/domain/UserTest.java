package domain;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Kees on 07/03/2017.
 */
public class UserTest extends User {

    public User user;

    public UserTest() {
        this.user = new User();
    }

    @Test
    public void testConcatenate() {
        User user = new User("Kees", "Werson");

        assertTrue(user.getTrue());
    }
}