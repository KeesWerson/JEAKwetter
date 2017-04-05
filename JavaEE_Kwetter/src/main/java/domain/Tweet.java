package domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Kees on 15/03/2017.
 */
@Entity
@NamedQuery(name = "Tweet.all", query = "SELECT t FROM Tweet t")
public class Tweet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String text;

    @ManyToOne
    private User userTweets;

    public Tweet(String text) {
        this.text = text;
    }

    public Tweet() {
    }

    public boolean getTrue(){
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
