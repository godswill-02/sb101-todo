package com.springboot101.todo.tasks.mappers;

import com.springboot101.todo.tasks.dtos.TaskCreateDto;
import com.springboot101.todo.tasks.entities.Task;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Mr Uche.
 *
 */
@Component
public class TaskCreateDtoToTask implements Function<TaskCreateDto, Task> {
    @Override
    public Task apply(TaskCreateDto taskCreateDto) {
        return Task.builder()
                .title(taskCreateDto.title())
                .content(taskCreateDto.content())
                .build();
    }
}
