package com.felipe.blog.auth_service.Service;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import com.felipe.blog.auth_service.Mapper.UserMapper;
import com.felipe.blog.auth_service.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

        User user = userRepository.findUserByUserName(userName).orElseThrow();

        return userMapper.mapToUserResponseDto(user);

    }

}
