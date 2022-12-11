package ru.unsleepingowl.katasecurity.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.unsleepingowl.katasecurity.model.Role;
import ru.unsleepingowl.katasecurity.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private final RoleDao roleDao;

    @Autowired
    public UserDaoImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void addUser(User user) {
            user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
            entityManager.persist(user);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user, Long id) {
        if (user.getPassword().equals("")) {
            user.setPassword(getUserById(id).getPassword());
        } else {
            user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        }
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersList() {
        return entityManager.createQuery("FROM User").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
    }

    @Override
    public User createUser() {
        User newUser = new User();
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleDao.findByAuthority("ROLE_USER"));
        newUser.setRoles(roles);
        return newUser;
    }
}
