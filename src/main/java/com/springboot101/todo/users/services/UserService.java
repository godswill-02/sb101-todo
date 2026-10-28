package com.springboot101.todo.users.services;

import com.springboot101.todo.users.dtos.UserCreateDto;
import com.springboot101.todo.users.dtos.UserResponseDto;
import com.springboot101.todo.users.dtos.UserUpdateDto;
import com.springboot101.todo.users.entities.User;
import com.springboot101.todo.users.mappers.UserCreateDtoToUser;
import com.springboot101.todo.users.mappers.UserToUserResponseDto;
import com.springboot101.todo.users.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr Uche.
 *
 */
@Service("user-service")
public class UserService {
    private final UserRepo userRepo;
    private final UserToUserResponseDto userToUserResponseDto;
    private final UserCreateDtoToUser userCreateDtoToUser;

    public UserService(UserRepo userRepo, UserToUserResponseDto userToUserResponseDto, UserCreateDtoToUser userCreateDtoToUser) {
        this.userRepo = userRepo;
        this.userToUserResponseDto = userToUserResponseDto;
        this.userCreateDtoToUser = userCreateDtoToUser;
    }

    public UserResponseDto save(UserCreateDto userCreateDto) {
        return userToUserResponseDto.apply(userRepo.save(userCreateDtoToUser.apply(userCreateDto)));
    }

    public void delete(Long id) {
        User dbUser = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        userRepo.delete(dbUser);
    }

    public UserResponseDto update(Long id, UserUpdateDto userUpdate) {
        User dbUser = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        if(userUpdate.email() != null) {
            dbUser.setEmail(userUpdate.email());
        }
        if(userUpdate.username() != null ){
            dbUser.setUsername(userUpdate.username());
        }
        User updatedUser = userRepo.save(dbUser);

        return userToUserResponseDto.apply(updatedUser);
    }
}
