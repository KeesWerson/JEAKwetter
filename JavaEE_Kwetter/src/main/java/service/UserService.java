package service;

import DAO.UserDAO;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kees on 08/03/2017.
 */

@Stateless
public class UserService {

    @Inject
    UserDAO userDAO;

    public List<User> allUsers() throws Exception {
        return userDAO.allUsers();
    }

    public boolean saveUser(User user){
        return userDAO.saveUser(user);
    }

    /**
     * Creates a new user
     * @param email : users email address
     * @param username : users username
     * @param password : users password
     * @return the created user
     */
    public User Create(String email, String username, String password) {

        User user = new User();
        user.setName(username);
        user.setEmail(email);
        user.setPassword(password);

        userDAO.saveUser(user);
        return user;
    }
}
