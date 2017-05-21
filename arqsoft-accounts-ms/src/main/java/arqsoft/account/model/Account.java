package arqsoft.account.model;

import javax.persistence.*;

/**
 * Created by javergarav on 15/02/2017.
 */

@Entity
@Table(name = "accounts")
@NamedQueries({@NamedQuery(name = Account.FIND_ALL, query = "SELECT u FROM Account u")})
public class Account {

    public static final String FIND_ALL = "Account.findAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long balance;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
