package com.springboot101.todo.tasks.dtos;

/**
 * @author Mr Uche.
 *
 */
public record TaskUpdateDto(
        String title,
        String content
) {
}
