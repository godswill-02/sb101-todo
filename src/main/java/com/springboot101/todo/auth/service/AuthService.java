package com.springboot101.todo.auth.service;

import com.springboot101.todo.auth.dtos.LoginDto;
import com.springboot101.todo.auth.dtos.RegisterDto;
import com.springboot101.todo.auth.dtos.TokenResponseDto;
import com.springboot101.todo.security.JwtProvider;
import com.springboot101.todo.users.entities.User;
import com.springboot101.todo.users.repositories.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

/**
 * @author Mr Uche.
 *
 */
@Service("auth-service")
public class AuthService {
    private final UserRepo userRepo;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepo userRepo, JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    public TokenResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtProvider.generateToken(authentication);
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProvider.getJwtExpirationMs());
        return new TokenResponseDto(accessToken, expirationDate);
    }

    public TokenResponseDto register(RegisterDto registerDto) {
    }
}
