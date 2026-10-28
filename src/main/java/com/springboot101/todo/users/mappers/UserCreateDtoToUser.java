package com.springboot101.todo.users.mappers;

import com.springboot101.todo.tasks.entities.Task;
import com.springboot101.todo.tasks.mappers.TaskCreateDtoToTask;
import com.springboot101.todo.users.dtos.UserCreateDto;
import com.springboot101.todo.users.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

/**
 * @author Mr Uche.
 *
 */
@Component
public class UserCreateDtoToUser implements Function<UserCreateDto, User> {

    private final TaskCreateDtoToTask taskCreateDtoToTask;

    public UserCreateDtoToUser(TaskCreateDtoToTask taskCreateDtoToTask) {
        this.taskCreateDtoToTask = taskCreateDtoToTask;
    }

    @Override
    public User apply(UserCreateDto userCreateDto) {
        // 1. D'abord, on construit le User sans ses tâches (ou on le prépare)
        User user = User.builder()
                .username(userCreateDto.username())
                .password(userCreateDto.password())
                .email(userCreateDto.email())
                .build();

        // 2. On transforme les DTOs en entités Task, et on associe le user à chaque tâche
        if (userCreateDto.taskCreateDto() != null) {
            List<Task> tasks = userCreateDto.taskCreateDto().stream()
                    .map(taskCreateDtoToTask)
                    .peek(task -> task.setUser(user)) // <--- C'est LA ligne magique qui manquait !
                    .toList();

            // 3. On injecte la liste de tâches reliées dans le user
            user.setTasks(tasks);
        }

        return user;
    }
}
