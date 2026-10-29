package com.springboot101.todo.users.repositories;

import com.springboot101.todo.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * @author Mr Uche.
 *
 */
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(
            value = """
                select  exists (
                select 1\s
                from users
                where email = :email
                or username = :username
                )
           \s""", nativeQuery = true
    )
    boolean existsByEmailOrUsername(@Param("username") String username, @Param("email") String email);
}
