package com.springboot101.todo.tasks.services;

import com.springboot101.todo.tasks.dtos.TaskCreateDto;
import com.springboot101.todo.tasks.dtos.TaskResponseDto;
import com.springboot101.todo.tasks.dtos.TaskUpdateDto;
import com.springboot101.todo.tasks.entities.Task;
import com.springboot101.todo.tasks.mappers.TaskCreateDtoToTask;
import com.springboot101.todo.tasks.mappers.TaskToTaskResponseDto;
import com.springboot101.todo.tasks.repositories.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr Uche.
 *
 */
@Service("task-service")
public class TaskService {
    private final TaskRepo taskRepo;
    private final TaskToTaskResponseDto taskToTaskResponseDto;
    private final TaskCreateDtoToTask taskCreateDtoToTask;
    public TaskService(TaskRepo taskRepo, TaskToTaskResponseDto taskToTaskResponseDto, TaskCreateDtoToTask taskCreateDtoToTask) {
        this.taskRepo = taskRepo;
        this.taskToTaskResponseDto = taskToTaskResponseDto;
        this.taskCreateDtoToTask = taskCreateDtoToTask;
    }

    public List<TaskResponseDto> listTasks() {

        return taskRepo.findAll()
                .stream()
                .map(taskToTaskResponseDto)
                .toList();
    }

    public TaskResponseDto saveTask(TaskCreateDto taskCreateDto) {
        return taskToTaskResponseDto.apply(taskRepo.save(taskCreateDtoToTask.apply(taskCreateDto)));
    }

    public void deleteTask(Long id) {
        Task dbTask = taskRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        taskRepo.delete(dbTask);
    }

    public TaskResponseDto updateTask(Long id, TaskUpdateDto taskUpdateDto) {
        Task dbTask = taskRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        Task task = Task.builder()
                .title(taskUpdateDto.title())
                .content(taskUpdateDto.content())
                .build();

        return taskToTaskResponseDto.apply(task);
    }

}
