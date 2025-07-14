package com.felipe.blog.auth_service.Dto.User;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String role;

}
