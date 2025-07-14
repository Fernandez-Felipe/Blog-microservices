package com.felipe.blog.auth_service.Controller;

import com.felipe.blog.auth_service.Dto.Login.LoginRequestDto;
import com.felipe.blog.auth_service.Dto.Login.LoginResponseDto;
import com.felipe.blog.auth_service.Service.AuthService;
import com.felipe.blog.auth_service.Service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private JwtService jwtService;

    @Mock
    private AuthService authService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void LoginShouldReturnAValidToken(){

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .usernameoremail("Felipe")
                .password("123456789")
                .build();

    }


}
