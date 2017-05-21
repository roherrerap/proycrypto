package arqsoft.authentication.resource;

import arqsoft.authentication.model.Session;
import arqsoft.authentication.service.SessionService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by javergarav on 28/03/2017.
 */

@Path("/sessions")
public class SessionResource {

    @Context
    UriInfo uriInfo;

    @EJB
    SessionService sessionService;

    @GET
    @Path("{userId}")
    public Session getSessionByUserId(@PathParam("userId") long userId) {
        return sessionService.getSessionByUserId(userId);
    }

    @POST
    public Response createSession(Session session) {
        sessionService.createSession(session);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{token}")
    public Response deleteUser(@PathParam("token") String token) {
        sessionService.deleteSession(token);
        return Response.status(Response.Status.OK).build();
    }

}
