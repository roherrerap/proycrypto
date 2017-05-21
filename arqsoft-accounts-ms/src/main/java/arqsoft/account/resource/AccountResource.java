package arqsoft.account.resource;

import arqsoft.account.model.Account;
import arqsoft.account.service.AccountService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by javergarav on 15/02/2017.
 */

@Path("/accounts")
public class AccountResource {

    @Context
    UriInfo uriInfo;

    @EJB
    AccountService accountService;

    @GET
    public List<Account> getAllAccounts(@QueryParam("first") int first, @QueryParam("maxResult") int maxResult) {
        return accountService.getAllAccounts(first, maxResult);
    }

    @GET
    @Path("{id}")
    public Account getAccountById(@PathParam("id") long id) {
        return accountService.getAccountById(id);
    }

    @POST
    public Response createAccount(Account account) {
        accountService.createAccount(account);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response updateAccount(@PathParam("id") long id, Account account) {
        accountService.updateAccount(id, account);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAccount(@PathParam("id") long id) {
        accountService.deleteAccount(id);
        return Response.status(Response.Status.OK).build();
    }

}
