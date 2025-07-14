package com.felipe.blog.auth_service.Controller;

import com.felipe.blog.auth_service.Dto.Login.LoginRequestDto;
import com.felipe.blog.auth_service.Dto.Login.LoginResponseDto;
import com.felipe.blog.auth_service.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<LoginResponseDto> Login(@RequestBody @Valid LoginRequestDto request){

        LoginResponseDto response = authService.Login(request);

        return ResponseEntity.ok(response);

    }

}
