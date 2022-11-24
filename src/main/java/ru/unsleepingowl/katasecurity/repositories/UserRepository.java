package ru.unsleepingowl.katasecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.unsleepingowl.katasecurity.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
