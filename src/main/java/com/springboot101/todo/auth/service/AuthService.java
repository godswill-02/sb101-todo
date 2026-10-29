package com.springboot101.todo.auth.service;

import com.springboot101.todo.auth.dtos.LoginDto;
import com.springboot101.todo.auth.dtos.RegisterDto;
import com.springboot101.todo.auth.dtos.TokenResponseDto;
import com.springboot101.todo.security.JwtProvider;
import com.springboot101.todo.users.dtos.UserResponseDto;
import com.springboot101.todo.users.entities.User;
import com.springboot101.todo.users.repositories.UserRepo;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, JwtProvider jwtProvider, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }
    public TokenResponseDto login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(loginDto.username(),loginDto.password());

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        String accessToken = jwtProvider.generateToken(authentication);
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProvider.getJwtExpirationMs());

        return new TokenResponseDto(accessToken, expirationDate);
    }

    public UserResponseDto register(RegisterDto registerDto) {
        boolean userExists = userRepo.existsByEmailOrUsername(registerDto.username(), registerDto.email());
        if (userExists) {
            throw new EntityExistsException("User already exists", null);
        }
        User newUser = User.builder()
                .username(registerDto.username())
                .password(passwordEncoder.encode(registerDto.password()))
                .email(registerDto.email())
                .build();
        userRepo.save(newUser);
        return new UserResponseDto(newUser.getId(), newUser.getUsername(), newUser.getEmail(), newUser.getCreatedAt());
    }
}
