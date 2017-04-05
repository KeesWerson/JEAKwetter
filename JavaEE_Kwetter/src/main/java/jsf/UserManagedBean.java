package jsf;

import domain.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kees on 03/04/2017.
 */
@Named("userBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @Inject
    UserService userService;

    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT o FROM User AS o";


    private User user;

    private String email;
    private String userName;
    private String password;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    private EntityManagerFactory myEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public UserManagedBean() {
        myEntityManagerFactory = Persistence.createEntityManagerFactory("Kwetter");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return myEntityManagerFactory;
    }



    // add user
    public String create() {
        userService.create(email, userName, password);

        return "login";
    }

    // save user
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(getUser());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "login";
    }

    // delete user
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            User entity = getCurrentEntity();
            entity = entityManager.merge(entity);
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "userList";
    }

    public User getCurrentEntity() {
        User entity = getEntityFromRequestParam();

        return entity == null ? user : entity;
    }

    public String login(){
        if(userService.login(userName, password) != null){
            return "index";
        }
        else{
            return "CreateUser";
        }
    }



    public String startCreate() {
        user = new User();
        return "createUser";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewUser";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editUser";
    }



    public User getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        User entity = (User) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        user = getCurrentEntity();
    }

    public User findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        User entity = entityManager.find(User.class, id);

        entityManager.close();

        return entity;
    }

}
