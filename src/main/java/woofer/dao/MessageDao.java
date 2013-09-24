package woofer.dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import woofer.entity.Message;
import woofer.entity.User;

@Repository
@Transactional
public class MessageDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    private static Logger logger =
            Logger.getLogger(MessageDao.class);
    
    public void insert(Message message) {
        entityManager.persist(message);
    }
    
    public void update(Message message) {
        entityManager.merge(message);
    }
    
    public void delete(Message message) {
        message.setDeleteDate(new Date());
        entityManager.merge(message);
    }
    
    public List<Message> getMessages(int firstResult, int maxResults) {
        return entityManager.createQuery(
                "FROM Message ORDER BY postDate DESC")
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
    
    public List<Message> getByQuery(String query, int firstResult, int maxResults) {
        return entityManager.createQuery(
                "FROM Message WHERE text LIKE :query ORDER BY postDate DESC")
                .setParameter("query", "%" + query + "%")
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
    
    public List<Message> getByUsername(String username, int firstResult, int maxResults) {
        return entityManager.createQuery(
                "FROM Message WHERE user.username = :username "
                + "ORDER BY postDate DESC")
                .setParameter("username", username)
                .setFirstResult(firstResult)
                .setMaxResults(maxResults)
                .getResultList();
    }
    
    public long getMessageCount(User user) {
        return (Long) entityManager.createQuery(
                "SELECT COUNT(*) FROM Message WHERE user = :user")
                .setParameter("user", user)
                .getSingleResult();
    }
    
}
