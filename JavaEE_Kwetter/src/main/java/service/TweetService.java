package service;

import DAO.TweetDAO;
import domain.Tweet;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Stateless
public class TweetService {

    @Inject
    TweetDAO tweetDAO;

    public List<Tweet> allTweets() throws Exception {
        return tweetDAO.allTweets();
    }
}
