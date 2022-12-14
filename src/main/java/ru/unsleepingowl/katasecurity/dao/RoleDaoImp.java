package ru.unsleepingowl.katasecurity.dao;

import org.springframework.stereotype.Repository;
import ru.unsleepingowl.katasecurity.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Set<Role> getRolesSet() {
        return new HashSet<>(entityManager.createQuery("FROM Role", Role.class).getResultList());
    }
}
