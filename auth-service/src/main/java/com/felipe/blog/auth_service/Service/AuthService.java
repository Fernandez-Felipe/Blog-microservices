package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.Login.LoginRequestDto;
import com.felipe.blog.auth_service.Dto.Login.LoginResponseDto;
import com.felipe.blog.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public LoginResponseDto Login(LoginRequestDto request){

        User user = userRepository.findUserByUserName(request.getUsernameoremail())
                .or(() ->userRepository.findUserByEmail(request.getUsernameoremail()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }

        String Token = jwtService.GenerateToken(user);

        return new LoginResponseDto(Token);

    }

}
