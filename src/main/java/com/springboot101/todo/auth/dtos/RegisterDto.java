package com.springboot101.todo.auth.dtos;

/**
 * @author Mr Uche.
 *
 */
public record RegisterDto(
        String username,
        String email,
        String password
) {
}
