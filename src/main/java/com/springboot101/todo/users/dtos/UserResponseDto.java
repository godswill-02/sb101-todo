package com.springboot101.todo.users.dtos;

import java.time.Instant;

/**
 * @author Mr Uche.
 *
 */
public record UserResponseDto(
        Long id,
        String username,
        String email,
        Instant createdAt
) {
}
