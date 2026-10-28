package com.springboot101.todo.tasks.mappers;

import com.springboot101.todo.tasks.dtos.TaskResponseDto;
import com.springboot101.todo.tasks.entities.Task;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Mr Uche.
 *
 */
@Component
public class TaskToTaskResponseDto implements Function<Task, TaskResponseDto> {

    @Override
    public TaskResponseDto apply(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContent(), task.getCreatedAt());
    }
}
