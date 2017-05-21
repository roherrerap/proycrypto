package arqsoft.transaction.resource;

import arqsoft.transaction.model.Transaction;
import arqsoft.transaction.service.TransactionService;

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

@Path("/transactions")
public class TransactionResource {

    @Context
    UriInfo uriInfo;

    @EJB
    TransactionService transactionService;

    @GET
    @Path("{idUser}")
    public Transaction getTransactionByUserId(@PathParam("idUser") long idUser) {
        return transactionService.consultTransaction(idUser);
    }

    @POST
    public Response createTransaction(Transaction tr) {
        transactionService.createTransaction(tr.getIdUser(), tr.getAccountStart(), tr.getAccountFinal(), tr.getAmount());
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{idUser}")
    public Response updateTransaction(@PathParam("idUser") long idUser, Transaction tr) {
        transactionService.updateTransaction(idUser, tr.getAccountStart(), tr.getAccountFinal(), tr.getAmount());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
