package arqsoft.authentication.resource;

import arqsoft.authentication.model.User;
import arqsoft.authentication.service.UserService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * Created by javergarav on 15/02/2017.
 */

@Path("/users")
public class UserResource {

    @Context
    UriInfo uriInfo;

    @EJB
    UserService userService;

    @GET
    public List<User> getAllUsers(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return userService.getAllUsers(first, maxResult);
    }

    @GET
    @Path("id/{id}")
    public User getUserById(@PathParam("id") long id) {
        return userService.getUserById(id);
    }

    @GET
    @Path("email/{email}")
    public User getUserByEmail(@PathParam("email") String email) {
        return userService.getUserByEmail(email);
    }

    @POST
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response updateUser(@PathParam("id") long id, User user) {
        userService.updateUser(id, user);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") long id) {
        userService.deleteUser(id);
        return Response.status(Response.Status.OK).build();
    }

}
