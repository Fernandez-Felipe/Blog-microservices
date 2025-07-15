package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp(){
        jwtService = new JwtService("clave-super-hiper-giga-ultra-tera-jota-recontra-secreta");
        jwtService.init();
    }

    @Test
    void generateToken_shouldReturnANotNullToken(){

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        String token = jwtService.GenerateToken(user);

        assertNotNull(token);
        assertFalse(token.isEmpty());

    }

}
