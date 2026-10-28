package com.springboot101.todo.auth.dtos;

import java.util.Date;

/**
 * @author Mr Uche.
 *
 */
public record TokenResponseDto(
        String accessToken,
        Date expiresAt
) {
}
