package jsf;

import domain.Tweet;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kees on 03/04/2017.
 */
public class TweetManagedBean {

    final public static String SELECT_ALL_ENTITIES_SQL = "SELECT t FROM Tweet AS t";

    private Tweet tweetEntity;

    private EntityManagerFactory tweetEntityManagerFactory;

    private ListDataModel myList;
    private ListDataModel myReferencesEntities; // M-N and M-1 references

    public TweetManagedBean() {
        tweetEntityManagerFactory = Persistence.createEntityManagerFactory("FirstApp");
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return tweetEntityManagerFactory;
    }

    public Tweet getEntity() {
        return tweetEntity;
    }

    public void setEntity(Tweet entity) {
        tweetEntity = entity;
    }

    // add new Tweet
    public String create() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();

        return "tweetList";
    }

    // save edited Tweet
    public String save() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            tweetEntity = entityManager.merge(getEntity());
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            try {
                entityManager.getTransaction().rollback();
            } catch (Exception e) {
            }
        }
        entityManager.close();
        return "tweetList";
    }

    // delete Tweet
    public String delete() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Tweet entity = getCurrentEntity();
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

        return "tweetList";
    }

    public DataModel getReferencedEntities() {
        return myReferencesEntities;
    }

    public void setReferencedEntities(Collection<Tweet> entities) {
        myReferencesEntities = new ListDataModel(new ArrayList<Tweet>(entities));
    }

    public String startCreate() {
        tweetEntity = new Tweet();
        return "createTweet";
    }

    public String startView() {
        setEntityFromRequestParam();
        return "viewTweet";
    }

    public String startEdit() {
        setEntityFromRequestParam();
        return "editTweet";
    }

    public Tweet getCurrentEntity() {
        Tweet entity = getEntityFromRequestParam();

        return entity == null ? tweetEntity : entity;
    }

    public Tweet getEntityFromRequestParam() {
        if (myList == null) return null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        Tweet entity = (Tweet) myList.getRowData();
        entity = entityManager.merge(entity);
        entityManager.close();

        return entity;
    }

    public void setEntityFromRequestParam() {
        tweetEntity = getCurrentEntity();
    }

    public Tweet findEntity(Long id) {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        Tweet entity = entityManager.find(Tweet.class, id);

        entityManager.close();

        return entity;
    }

    public DataModel getAllEntities() {
        myList = new ListDataModel(getEntities());

        return myList;
    }

    public SelectItem[] getAllEntitiesAsSelectedItems() {
        List<Tweet> entities = getEntities();
        SelectItem selectItems[] = new SelectItem[entities.size()];
        int i = 0;
        for (Tweet entity : entities) {
            selectItems[i++] = new SelectItem(entity);
        }
        return selectItems;
    }

    public List<Tweet> getEntities() {
        EntityManager entityManager = getEntityManagerFactory().createEntityManager();

        List<Tweet> entities = (List<Tweet>) entityManager.createQuery(SELECT_ALL_ENTITIES_SQL).getResultList();

        entityManager.close();

        return entities;
    }
}
