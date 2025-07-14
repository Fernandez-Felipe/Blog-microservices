package com.felipe.blog.auth_service.Controller;

import com.felipe.blog.auth_service.Dto.User.UserDto;
import com.felipe.blog.auth_service.Dto.User.UserResponseDto;
import com.felipe.blog.auth_service.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping
    public UserResponseDto RegisterNewUser(@RequestBody @Valid UserDto userDto){
        return userService.addUser(userDto);
    }

}
