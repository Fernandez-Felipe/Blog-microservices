package com.felipe.blog.auth_service.Dto.Login;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequestDto {

    @NotNull
    private String usernameoremail;

    @NotNull
    private String password;

}
