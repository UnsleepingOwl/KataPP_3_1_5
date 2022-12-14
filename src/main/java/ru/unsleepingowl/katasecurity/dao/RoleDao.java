package ru.unsleepingowl.katasecurity.dao;

import ru.unsleepingowl.katasecurity.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getRolesSet();
}
