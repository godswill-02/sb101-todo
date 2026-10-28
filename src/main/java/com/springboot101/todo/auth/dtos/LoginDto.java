package com.springboot101.todo.auth.dtos;

/**
 * @author Mr Uche.
 *
 */
public record LoginDto(
        String username,
        String password
) {
}
