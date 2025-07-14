package com.felipe.blog.auth_service.Mapper;

import com.felipe.blog.auth_service.Domain.User;
import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User MapToUser(UserDto userDto){
        return User.builder().id(userDto.getId())
                             .username(userDto.getUsername())
                             .email(userDto.getEmail())
                             .password(userDto.getPassword())
                             .role(userDto.getRole())
                             .build();
    }

    public UserResponseDto mapToUserResponseDto(User user){

        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
