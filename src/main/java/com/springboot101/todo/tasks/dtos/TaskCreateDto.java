package com.springboot101.todo.tasks.dtos;

/**
 * @author Mr Uche.
 *
 */
public record TaskCreateDto(
        String title,
        String content
) {
}
