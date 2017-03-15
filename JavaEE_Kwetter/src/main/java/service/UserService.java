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

    public List<User> allUsers(){
        return userDAO.allUsers();
    }
}
