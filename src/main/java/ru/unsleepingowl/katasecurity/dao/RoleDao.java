package ru.unsleepingowl.katasecurity.dao;

import ru.unsleepingowl.katasecurity.model.Role;

import java.util.Set;

public interface RoleDao {
    Role findByAuthority(String authority);

    void addRole(Role role);

    Set<Role> getRolesSet();
}
