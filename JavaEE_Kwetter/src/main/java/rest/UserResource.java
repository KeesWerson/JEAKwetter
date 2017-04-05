package rest;

import JSON.UserJSON;
import domain.User;
import service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Kees on 08/03/2017.
 */

@Stateless
@Path("users")
public class UserResource {
    @Inject
    UserService userService;

    public UserResource() {
    }

    @GET
    public List<User> allUsers() throws Exception {
        return userService.allUsers();
    }

    @POST
    @Consumes("application/json")
    @Path("create")
    public User create(final UserJSON jasonObject){
        return userService.create(jasonObject.email, jasonObject.name, jasonObject.password);
    }

    @POST
    public boolean saveUser(User user){
        return userService.saveUser(user);
    }
}
