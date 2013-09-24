package woofer.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import woofer.entity.User;
import woofer.service.PasswordEncryptionService;

@Repository
@Transactional
public class UserDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private PasswordEncryptionService pes;
    
    private static Logger logger =
            Logger.getLogger(UserDao.class);
    
    public void insert(User user) {
        entityManager.persist(user);
    }
    
    public void update(User user) {
        entityManager.merge(user);
    }
    
    public void delete(User user) {
        entityManager.remove(user);
    }
    
    public User getByCredentials(String username, String clearTextPassword)
            throws NoResultException {

        // Query for user based on username
        User user = getByUsername(username);
        
        // Encrypt provided password with that user's salt
        // If encryption fails, then return null.
        String encryptedPassword = null;
        try {
            encryptedPassword = pes.getEncryptedPassword(
                clearTextPassword, user.getSalt());
        } catch(Exception e) {
            logger.fatal(e.fillInStackTrace());
        }
        // See if the passwords match
        if(user.getPassword().equals(encryptedPassword)) {
            return user;
        }
        throw new NoResultException();
    }
    
    public boolean emailRegistered(String email) {
        try {
            entityManager.createQuery(
                "FROM User WHERE LOWER(email) = :email")
                .setParameter("email", email.toLowerCase())
                .getSingleResult();
            return true;
        } catch(NoResultException e) {
            return false;
        }
    }
    
    public boolean userExists(String username) {
        try {
            getByUsername(username);
            return true;
        } catch(NoResultException e) {
            return false;
        }
    }
    
    /**
     * Obtain a User by username.
     * Public API shouldn't try to access a user without a password.
     * Thus this method is private for internal use only.
     * @param username 
     */
    private User getByUsername(String username) throws NoResultException {        
        return (User) entityManager.createQuery(
            "FROM User WHERE LOWER(username) = :username")
            .setParameter("username", username.toLowerCase())
            .getSingleResult();
    }
}
