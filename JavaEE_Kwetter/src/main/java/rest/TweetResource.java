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
@Path("tweets")
public class TweetResource {

    @Inject
    TweetService tweetService;

    public TweetResource() {
    }

    @GET
    public List<Tweet> allTweets() throws Exception {
        return tweetService.allTweets();
    }
}
