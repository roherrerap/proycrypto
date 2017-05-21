package arqsoft.transaction.model;

import com.mongodb.BasicDBObject;

/**
 * Created by javergarav on 14/03/2017.
 */

public class Transaction {

    private long idUser;
    private long accountStart;
    private long accountFinal;
    private long amount;

    public Transaction() {
    }

    public long getIdUser() {
      return idUser;
    }

    public void setIdUser(long idUser) {
      this.idUser = idUser;
    }

    public long getAccountStart() {
      return accountStart;
    }

    public void setAccountStart(long accountStart) {
      this.accountStart = accountStart;
    }

    public long getAccountFinal() {
      return accountFinal;
    }

    public void setAccountFinal(long accountFinal) {
      this.accountFinal = accountFinal;
    }

    public long getAmount() {
      return amount;
    }

    public void setAmount(long amount) {
      this.amount = amount;
    }

    public Transaction(long idUser, long accountStart, long accountFinal, long amount) {
      this.idUser = idUser;
      this.accountStart = accountStart;
      this.accountFinal = accountFinal;
      this.amount = amount;
    }

    public Transaction(BasicDBObject dBObjectTransaction) {
        this.idUser = dBObjectTransaction.getLong("idUser");
        this.accountStart = dBObjectTransaction.getLong("AccountStart");
        this.accountFinal = dBObjectTransaction.getLong("AccountFinal");
        this.amount = dBObjectTransaction.getLong("Amount");
    }

    public BasicDBObject toDBObjectMenu() {
	     // Creamos una instancia BasicDBObject
       BasicDBObject dBObjectTransaction = new BasicDBObject();
       dBObjectTransaction.append("idUser", this.getIdUser());
       dBObjectTransaction.append("AccountStart", this.getAccountStart());
       dBObjectTransaction.append("AccountFinal", this.getAccountFinal());
       dBObjectTransaction.append("Amount", this.getAmount());
       return dBObjectTransaction;
    }

}
