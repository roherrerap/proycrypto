package arqsoft.authentication.service;

import arqsoft.authentication.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by javergarav on 15/02/2017.
 */

@Stateless
public class UserService {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> getAllUsers(int first, int maxResult) {
        return entityManager.createNamedQuery(User.FIND_ALL)
                .setFirstResult(first).setMaxResults(maxResult).getResultList();
    }

    public User getUserById(long id){
        return entityManager.find(User.class, id);
    }

    public User getUserByEmail(String email){
        return entityManager.createNamedQuery(User.FIND_BY_EMAIL, User.class)
                .setParameter("email", email).getSingleResult();
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public User updateUser(long id, User user) {
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        return entityManager.merge(userToUpdate);
    }

    public void deleteUser(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

}
