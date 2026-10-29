package com.springboot101.todo.auth.controller;

import com.springboot101.todo.auth.dtos.LoginDto;
import com.springboot101.todo.auth.dtos.RegisterDto;
import com.springboot101.todo.auth.dtos.TokenResponseDto;
import com.springboot101.todo.auth.service.AuthService;
import com.springboot101.todo.users.dtos.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Mr Uche.
 *
 */
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        try {
            TokenResponseDto token = authService.login(loginDto);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        }
    }


    @PostMapping(path = "/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            UserResponseDto user = authService.register(registerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
