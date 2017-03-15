package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kees on 15/03/2017.
 */
@Entity
@NamedQuery(name = "Heart.all", query = "SELECT h FROM Heart h")
public class Heart implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    Date postDate;

    @OneToMany
    private List<User> userHearts;

    @OneToMany
    private List<Tweet> tweetHearts;

    public Heart() {
    }
}
