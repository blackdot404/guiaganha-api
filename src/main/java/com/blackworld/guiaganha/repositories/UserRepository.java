package com.blackworld.guiaganha.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blackworld.guiaganha.entities.User;


public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

}
