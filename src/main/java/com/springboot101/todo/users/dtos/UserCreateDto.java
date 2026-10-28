package com.springboot101.todo.users.dtos;

import com.springboot101.todo.tasks.dtos.TaskCreateDto;

import java.util.List;

/**
 * @author Mr Uche.
 *
 */
public record UserCreateDto(
        String username,
        String email,
        String password,
        List<TaskCreateDto> taskCreateDto
) {
}
