package DAO;

import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Kees on 15/03/2017.
 */
public class UserDAOTest {

    User user;
    UserDAO userDAO;
    EntityManager entityManager;

    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAO();

        entityManager = mock(EntityManager.class);
        userDAO.setEntityManager(entityManager);

        user = new User();
        user.setId((long) 1);
    }

    @Test
    public void allUsers() throws Exception {
        Query query = mock(Query.class);
        when(entityManager.createNamedQuery("User.all")).thenReturn(query);

        List<User> dummyResult = new ArrayList<>();
        when(query.getResultList()).thenReturn(dummyResult);

        //test
        List<User> result = userDAO.allUsers();

        //verify
        verify(entityManager).createNamedQuery("User.all");
        verify(query).getResultList();
        assertSame(dummyResult, result);
    }

    @Test
    public void allFollowers() throws Exception {
        Query query = mock(Query.class);
        when(entityManager.createNamedQuery("User.allFollowers")).thenReturn(query);
        when(query.setParameter("user", user)).thenReturn(query);

        List<User> dummyResult = new ArrayList<>();
        when(query.getResultList()).thenReturn(dummyResult);

        //test
        List<User> result = userDAO.allFollowers(user);

        //verify
        verify(entityManager).createNamedQuery("User.allFollowers");
        verify(query).getResultList();
        assertSame(dummyResult, result);
    }

    @Test
    public void saveUser() throws Exception {
        User test = new User();
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                User test1 = (User)invocationOnMock.getArguments()[0];
                test1.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(User.class));

        //test
        userDAO.saveUser(test);

        //verify
        assertNotNull(test.getId());
    }

    @Test
    public void removeUser() throws Exception {
        return;
    }

}