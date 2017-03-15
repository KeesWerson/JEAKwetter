package rest;

import domain.Tweet;
import service.TweetService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Stateless
@Path("tweet")
public class TweetResource {

    @Inject
    TweetService tweetService;

    @GET
    public List<Tweet> allTweets(){
        return tweetService.allTweets();
    }
}
