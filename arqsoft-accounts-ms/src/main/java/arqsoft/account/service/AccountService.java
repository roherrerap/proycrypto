package arqsoft.account.service;

import arqsoft.account.model.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by javergarav on 15/02/2017.
 */

@Stateless
public class AccountService {

    @PersistenceContext
    EntityManager entityManager;

    public List<Account> getAllAccounts(int first, int maxResult) {
        return entityManager.createNamedQuery(Account.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public Account getAccountById(long id){
        return entityManager.find(Account.class, id);
    }

    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    public Account updateAccount(long id, Account account) {
        Account accountToUpdate = entityManager.find(Account.class, id);
        accountToUpdate.setBalance(account.getBalance());
        accountToUpdate.setUserId(account.getUserId());
        return entityManager.merge(accountToUpdate);
    }

    public void deleteAccount(long id) {
        Account account = entityManager.find(Account.class, id);
        entityManager.remove(account);
    }

}
