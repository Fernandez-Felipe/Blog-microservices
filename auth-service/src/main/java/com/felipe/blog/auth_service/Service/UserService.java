package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import com.felipe.blog.auth_service.Mapper.UserMapper;
import com.felipe.blog.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDto addUser(UserDto userDto){

        String EncryptedPassword = passwordEncoder.encode(userDto.getPassword());

        User newUser = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(EncryptedPassword)
                .role(userDto.getRole())
                .build();

        userRepository.save(newUser);

        return userMapper.mapToUserResponseDto(newUser);

    }

    public UserResponseDto findUserByName(String userName){

        User user = userRepository.findUserByUsername(userName).orElseThrow();

        return userMapper.mapToUserResponseDto(user);

    }

    public UserResponseDto findUserByEmail(String email){
        User user = userRepository.findUserByEmail(email).orElseThrow();
        return userMapper.mapToUserResponseDto(user);
    }

}
