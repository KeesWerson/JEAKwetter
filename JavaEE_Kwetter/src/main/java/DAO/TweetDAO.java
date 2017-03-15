package DAO;

import domain.Tweet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Stateless
public class TweetDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<Tweet> allTweets() {
        return entityManager.createNamedQuery("Tweet.all").getResultList();
    }

    public void saveTweet(Tweet tweet){
        entityManager.persist(tweet);
    }

    public boolean removeUser(Tweet tweet){
        entityManager.remove(tweet);
        return true;
    }
}
