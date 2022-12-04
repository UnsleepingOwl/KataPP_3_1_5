package ru.unsleepingowl.katasecurity.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.unsleepingowl.katasecurity.dao.RoleDao;
import ru.unsleepingowl.katasecurity.model.Role;

import java.util.List;
import java.util.Set;


@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getRolesSet() {
        return roleDao.getRolesSet();
    }
}
