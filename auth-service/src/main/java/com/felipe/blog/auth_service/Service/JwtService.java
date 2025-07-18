package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@Getter
public class JwtService {

    private final String SECRET_KEY;

    public JwtService(@Value("${jwt.SECRET_KEY}") String SECRET_KEY){
        this.SECRET_KEY = SECRET_KEY;
    }

    private Key key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String GenerateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role",user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();
    }

}
