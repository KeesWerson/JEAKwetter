package domain;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Kees on 15/03/2017.
 */
public class TweetTest {

    public Tweet tweet;

    public TweetTest() {
        this.tweet = new Tweet();
    }

    @Test
    public void testConcatenate() {
        Tweet tweet = new Tweet("Lekker weertje meneertje!");

        assertTrue(tweet.getTrue());
    }

}