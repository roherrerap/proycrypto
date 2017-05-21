package arqsoft.authentication.service;

import arqsoft.authentication.model.Session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.SecureRandom;

/**
 * Created by javergarav on 28/03/2017.
 */

@Stateless
public class SessionService {

    @PersistenceContext
    EntityManager entityManager;

    public Session getSessionByUserId(long userId){
        return entityManager.find(Session.class, userId);
    }

    public void createSession(Session session) {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        session.setToken(token);
        entityManager.persist(session);
    }

    public void deleteSession(String token) {
        Session session = entityManager.find(Session.class, token);
        entityManager.remove(session);
    }

}
