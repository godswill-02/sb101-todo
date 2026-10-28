package com.springboot101.todo.users.mappers;

import com.springboot101.todo.users.dtos.UserResponseDto;
import com.springboot101.todo.users.entities.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Mr Uche.
 *
 */
@Component
public class UserToUserResponseDto implements Function<User, UserResponseDto> {
    @Override
    public UserResponseDto apply(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }
}
