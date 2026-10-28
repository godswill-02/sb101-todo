package com.springboot101.todo.tasks.dtos;

import java.time.Instant;

/**
 * @author Mr Uche.
 *
 */
public record TaskResponseDto(
        Long id,
        String title,
        String content,
        Instant createdAt
) {
}
