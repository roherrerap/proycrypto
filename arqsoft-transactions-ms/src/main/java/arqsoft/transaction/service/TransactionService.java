package arqsoft.transaction.service;

import arqsoft.transaction.model.Transaction;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

 /**
  * Created by javergarav on 14/03/2017.
  */
public class TransactionService {

    //@PersistenceContext
    //EntityManager entityManager;

    public static String collName = "transactions";

    public static String createTransaction(Long idUser, Long accountStart, Long accountFinal, Long amount) {
        System.out.println(idUser + " " + accountStart + " " + accountFinal + " " + amount);

        String transactionCreate = null;
        try {
            // To connect to mongo dbserver
            System.out.println("MongoConnection");
            MongoConnection dbSingleton = MongoConnection.getInstance();
            System.out.println("Successfully Conection");
            System.out.println("Getting Test db");
            DB db = dbSingleton.getTestdb();
            System.out.println("Successfully Test db");
            System.out.println("Getting Collection");
            DBCollection coll = db.getCollection(collName);
            System.out.println("Successfully collection");

            BasicDBObject doc = new BasicDBObject("idUser", idUser).
                    append("AccountStart", accountStart).
                    append("AccountFinal", accountFinal).
                    append("Amount", amount);
            coll.insert(doc);
            System.out.println("Document inserted successfully");
            transactionCreate = "Success";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if (transactionCreate != null) {
            return "Transaction done.";
        } else {
            return "Transaction don't work.";
        }
    }

    public static Transaction consultTransaction(Long idUser) {
        Transaction tr = new Transaction();
        try {
            // To connect to mongo dbserver
            MongoConnection dbSingleton = MongoConnection.getInstance();
            DB db = dbSingleton.getTestdb();
            DBCollection coll = db.getCollection(collName);
            BasicDBObject whereQuery = new BasicDBObject();
            // Sentence to search one account number
            whereQuery.put("idUser", idUser);
            DBCursor cursor = coll.find(whereQuery);
            while (cursor.hasNext()) {
                DBObject consultDocument = cursor.next();
                tr.setIdUser(idUser);
                tr.setAccountStart((Long) consultDocument.get("AccountStart"));
                tr.setAccountFinal((Long) consultDocument.get("AccountFinal"));
                tr.setAmount((Long) consultDocument.get("Amount"));
            }
            System.out.println("Document consulted successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return tr;
    }

    public static String updateTransaction(Long idUser, Long accountStart, Long accountFinal, Long amount) {
        String transactionUpdate = null;
        try {
            // To connect to mongo dbserver
            MongoConnection dbSingleton = MongoConnection.getInstance();
            DB db = dbSingleton.getTestdb();
            DBCollection coll = db.getCollection(collName);
            BasicDBObject whereQuery = new BasicDBObject();
            // Sentence to search one account number
            whereQuery.put("idUser", idUser);
            DBCursor cursor = coll.find(whereQuery);
            while (cursor.hasNext()) {
                DBObject updateDocument = cursor.next();
                updateDocument.put("AccountStart", accountStart);
                updateDocument.put("AccountFinal", accountFinal);
                updateDocument.put("Amount", amount);
                coll.update(whereQuery, updateDocument);
            }
            System.out.println("Document updated successfully");
            transactionUpdate = "Success";
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        if (transactionUpdate != null) {
            return "Has been updated.";
        } else {
            return "Has not been updated.";
        }
    }
}
