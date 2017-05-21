package arqsoft.authentication.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by javergarav on 28/03/2017.
 */

@Entity
@Table(name = "sessions")
public class Session {

    private String token;

    @Id
    private long userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
