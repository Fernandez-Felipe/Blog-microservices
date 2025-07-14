package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

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

    @Test
    void ShouldGetTheUserNameSuccesfully(){

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        String token = jwtService.GenerateToken(user);

        assertEquals("Felipe",jwtService.extractUsername(token));
    }

    @Test
    void ShoulBeFalseIfTokenIsAlreadyExpired(){

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        String token = jwtService.GenerateToken(user);

        assertFalse(jwtService.isTokenExpired(token));

    }

    @Test
    void ShouldBeTrueIfTokenIsAlreadyExpired() {

        User user = User.builder()
                .username("Felipe")
                .email("feliandres57@gmail.com")
                .password("12345")
                .role("USER")
                .build();

        String ExpiredToken = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() - 1000 * 60))
                .signWith(SignatureAlgorithm.HS256, jwtService.getKey())
                .compact();

        assertTrue(jwtService.isTokenExpired(ExpiredToken));


    }

}
