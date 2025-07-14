package com.felipe.blog.auth_service.Dto.Login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {

    @NotNull
    private String token;

}
