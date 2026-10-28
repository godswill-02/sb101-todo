package com.springboot101.todo.tasks.controllers;

import com.springboot101.todo.tasks.dtos.TaskCreateDto;
import com.springboot101.todo.tasks.dtos.TaskResponseDto;
import com.springboot101.todo.tasks.dtos.TaskUpdateDto;
import com.springboot101.todo.tasks.services.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr Uche.
 *
 */
@RestController
@RequestMapping(path = "tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponseDto> search() {
        return taskService.listTasks();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TaskResponseDto save(@RequestBody TaskCreateDto taskCreateDto) {
        return taskService.saveTask(taskCreateDto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @PutMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public TaskResponseDto update(@PathVariable Long id, @RequestBody TaskUpdateDto taskUpdateDto) {
        return taskService.updateTask(id, taskUpdateDto);
    }
}
