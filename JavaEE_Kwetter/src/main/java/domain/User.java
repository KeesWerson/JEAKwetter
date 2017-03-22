package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Kees on 07/03/2017.
 */
@Entity
@Table(name = "KwetterUser")
@NamedQueries({@NamedQuery(name = "User.allFollowers", query = "SELECT u FROM User as u WHERE :user MEMBER OF u.following"),
               @NamedQuery(name = "User.all", query = "SELECT u FROM User u")})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @ManyToMany
    private List<User> following;

    @OneToMany(mappedBy = "userTweets")
    private List<Tweet> tweets;


    String name;
    String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public boolean saveUser(User user){

    }*/
}
