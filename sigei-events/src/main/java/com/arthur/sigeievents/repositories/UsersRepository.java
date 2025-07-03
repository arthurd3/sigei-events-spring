package com.arthur.sigeievents.repositories;

import com.arthur.sigeievents.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User,Long> {

    Optional<User> findUsersByEmail(String email);
}
