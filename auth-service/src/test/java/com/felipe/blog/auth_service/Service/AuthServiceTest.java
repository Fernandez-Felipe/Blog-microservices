package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.Login.LoginRequestDto;
import com.felipe.blog.auth_service.Dto.Login.LoginResponseDto;
import com.felipe.blog.auth_service.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void A_Success_Login_Should_Return_A_Valid_Token(){

        LoginRequestDto request = LoginRequestDto.builder()
                .usernameoremail("Felipe")
                .password("123456789")
                .build();

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("hashed12345")
                .role("USER")
                .build();


        when(userRepository.findUserByUserName("Felipe")).thenReturn(Optional.ofNullable(user));
        Assertions.assertNotNull(user);
        when(passwordEncoder.matches(request.getPassword(),user.getPassword())).thenReturn(true);

        LoginResponseDto response = authService.Login(request);

        String token = jwtService.GenerateToken(user);

        assertEquals(token,response.getToken());

    }

}
