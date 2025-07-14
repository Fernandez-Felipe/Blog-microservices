package com.felipe.blog.auth_service.Controller;

import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import com.felipe.blog.auth_service.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterController registerController;

    @Test
    void RegisterShouldBeSucces(){

        UserDto userDto = UserDto.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .role("USER")
                .build();

        when(userService.addUser(any(UserDto.class))).thenReturn(userResponseDto);

        UserResponseDto Return = registerController.RegisterNewUser(userDto);

        assertEquals("Felipe",Return.getUsername());
        assertEquals("feliandres57@gmail.com",Return.getEmail());
        assertEquals("USER",Return.getRole());

        verify(userService).addUser(any(UserDto.class));

    }

}
