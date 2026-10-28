package com.springboot101.todo.users.controllers;

import com.springboot101.todo.users.dtos.UserCreateDto;
import com.springboot101.todo.users.dtos.UserResponseDto;
import com.springboot101.todo.users.dtos.UserUpdateDto;
import com.springboot101.todo.users.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mr Uche.
 *
 */
@RestController
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserResponseDto save(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @PatchMapping(value = "{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserUpdateDto userUpdate) {
        return userService.update(id, userUpdate);
    }
}
