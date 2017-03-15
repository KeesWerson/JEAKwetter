package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Entity
@NamedQuery(name = "Mention.all", query = "SELECT m FROM Mention m")
public class Mention implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    Date mentionDate;

    @OneToMany
    private List<User> mentionedUsers;

    @OneToMany
    private List<Tweet> mentionedTweets;

    public Mention() {
    }
}
