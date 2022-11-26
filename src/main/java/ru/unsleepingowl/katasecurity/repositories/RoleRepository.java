package ru.unsleepingowl.katasecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.unsleepingowl.katasecurity.model.Role;
import ru.unsleepingowl.katasecurity.model.User;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
