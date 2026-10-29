package com.springboot101.todo.tasks.mappers;

import com.springboot101.todo.tasks.dtos.TaskCreateDto;
import com.springboot101.todo.tasks.entities.Task;
import com.springboot101.todo.users.entities.User;
import com.springboot101.todo.users.repositories.UserRepo;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author Mr Uche.
 *
 */

@Component
public class TaskCreateDtoToTask implements Function<TaskCreateDto, Task> {

    private final UserRepo userRepo;

    public TaskCreateDtoToTask(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Task apply(TaskCreateDto taskCreateDto) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new IllegalStateException("User is not authenticated");
        }

        User user = userRepo.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Task.builder()
                .title(taskCreateDto.title())
                .content(taskCreateDto.content())
                .user(user)
                .build();
    }
}
