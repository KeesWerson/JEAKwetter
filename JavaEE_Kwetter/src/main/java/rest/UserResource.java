package rest;

import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

/**
 * Created by Kees on 08/03/2017.
 */

@Stateless
@Path("user")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    public List<User> allUsers(){
        return userService.allUsers();
    }
}
