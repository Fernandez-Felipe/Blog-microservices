package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import com.felipe.blog.auth_service.Mapper.UserMapper;
import com.felipe.blog.auth_service.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldAddUserSuccesfully(){

        UserDto userDto = UserDto.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        User savedUser = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("hashed123")
                .role("USER")
                .build();

        UserResponseDto responseDto = UserResponseDto.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .role("USER")
                .build();


        when(passwordEncoder.encode("12345")).thenReturn("hashed123");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapper.mapToUserResponseDto(savedUser)).thenReturn(responseDto);


        UserResponseDto result = userService.addUser(userDto);


        assertEquals("Felipe", result.getUsername());
        assertEquals("feliandres57@gmail.com", result.getEmail());
        assertEquals("USER",result.getRole());

        verify(passwordEncoder).encode("12345");
        verify(userRepository).save(any(User.class));
        verify(userMapper).mapToUserResponseDto(savedUser);

    }

    @Test
    void getUserByNameSuccesfully(){

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .role("USER")
                .build();

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        when(userRepository.findUserByUserName("Felipe")).thenReturn(Optional.ofNullable(user));
        when(userMapper.mapToUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        UserResponseDto Result = userService.findUserByName("Felipe");

        assertEquals("Felipe", Result.getUsername());
        assertEquals("feliandres57@gmail.com",Result.getEmail());
        assertEquals("USER",Result.getRole());

        verify(userRepository).findUserByUserName("Felipe");
        verify(userMapper).mapToUserResponseDto(any(User.class));

    }

}
