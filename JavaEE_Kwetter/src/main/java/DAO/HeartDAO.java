package DAO;

import domain.Heart;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Stateless
public class HeartDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Heart> allHearts() throws Exception {
        return entityManager.createNamedQuery("Heart.all").getResultList();
    }

    public void saveHeart(Heart heart){
        entityManager.persist(heart);
    }

    public boolean removeHeart(Heart heart){
        entityManager.remove(heart);
        return true;
    }
}
