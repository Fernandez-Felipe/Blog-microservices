package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application.properties")
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
