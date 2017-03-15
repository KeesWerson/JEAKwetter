package DAO;

import domain.Mention;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Stateless
public class MentionDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Mention> allMentions() {
        return entityManager.createNamedQuery("Mention.all").getResultList();
    }

    public void saveMention(Mention mention){
        entityManager.persist(mention);
    }

    public boolean removeMention(Mention mention){
        entityManager.remove(mention);
        return true;
    }
}
