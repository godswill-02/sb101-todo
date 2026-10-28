package com.springboot101.todo.users.repositories;

import com.springboot101.todo.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Mr Uche.
 *
 */
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
