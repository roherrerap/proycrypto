package arqsoft.bank.model;

/**
 * Created by javergarav on 01/03/2017.
 */
public class Session {

    private long userId;
    private String token;

    public Session(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Session(long userId, String email, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
