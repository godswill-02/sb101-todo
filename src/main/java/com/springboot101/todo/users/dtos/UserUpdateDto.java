package com.springboot101.todo.users.dtos;

/**
 * @author Mr Uche.
 *
 */
public record UserUpdateDto(
        String username,
        String email
) {
}
