package com.springboot101.todo.auth.controller;

import com.springboot101.todo.auth.dtos.LoginDto;
import com.springboot101.todo.auth.dtos.RegisterDto;
import com.springboot101.todo.auth.dtos.TokenResponseDto;
import com.springboot101.todo.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(path = "login")
    public TokenResponseDto login(LoginDto loginDto) {
        return authService.login(loginDto);
    }

    @PostMapping(path = "register")
    public TokenResponseDto register(RegisterDto registerDto) {
        return authService.register(registerDto);
    }
}
