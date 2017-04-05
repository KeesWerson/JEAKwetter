package DAO;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kees on 08/03/2017.
 */

@Stateless
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> allUsers() throws Exception {
        return entityManager.createNamedQuery("User.all").getResultList();
    }

    public List<User> allFollowers(User user) { return entityManager.createNamedQuery("User.allFollowers").setParameter("user", user).getResultList(); }

    public boolean saveUser(User user){
        entityManager.persist(user);
        return true;
    }

    public boolean removeUser(User user){
        entityManager.remove(user);
        return true;
    }

    public User findUserByCredentials(String username, String password){
        for (Object user : entityManager.createNamedQuery("User.all").getResultList()) {
            User foundUser = (User)user;
            if(foundUser.getName().equals(username) && foundUser.getPassword().equals(password)){
                return foundUser;
            }
        }
        return null;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
