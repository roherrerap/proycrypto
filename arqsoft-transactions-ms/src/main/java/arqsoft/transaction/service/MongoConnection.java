package arqsoft.transaction.service;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import java.util.Arrays;

public class MongoConnection {

    private static MongoConnection mDb;
    private static MongoClient mongoClient;
    private static DB db;
    private static final String dbName = "transaction";

    private MongoConnection() {
    }

    public static MongoConnection getInstance() {
        if (mDb == null) {
            mDb = new MongoConnection();
        }
        return mDb;
    }

    public DB getTestdb() {
        if (mongoClient == null) {
            mongoClient = new MongoClient("mongodb", 27017);
        }
        if (db == null) {
            db = mongoClient.getDB(dbName);
        }

        return db;
    }
}
