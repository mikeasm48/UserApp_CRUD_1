package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }
    public User getUser(int id) {
        return  entityManager.find(User.class, id);
    }

    public void createUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(int id, User updatedUser) {
        entityManager.merge(updatedUser);
    }

    public void deleteUser(int id) {
        User userById = getUser(id);
        entityManager.remove(userById);
    }
}
