package com.felipe.blog.auth_service.Controller;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.Login.LoginRequestDto;
import com.felipe.blog.auth_service.Dto.Login.LoginResponseDto;
import com.felipe.blog.auth_service.Repository.UserRepository;
import com.felipe.blog.auth_service.Service.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void LoginShouldReturnAValidToken(){

        LoginRequestDto request = LoginRequestDto.builder()
                .usernameoremail("feliandres57@gmail.com")
                .password("123456789")
                .build();

        LoginResponseDto response = new LoginResponseDto("new token");
        String Token = response.getToken();

        when(authService.Login(request)).thenReturn(response);

        assertEquals(Objects.requireNonNull(loginController.Login(request).getBody()).getToken(),Token);

        verify(authService).Login(request);

    }


}
