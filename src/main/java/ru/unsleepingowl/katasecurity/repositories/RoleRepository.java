package ru.unsleepingowl.katasecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unsleepingowl.katasecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
