package com.todo.www.todo.user.controller;

import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.user.dto.CreateUserDto;
import com.todo.www.todo.user.dto.LoginUserDto;
import com.todo.www.todo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody CreateUserDto createUserDto){
        return userService.signUp(createUserDto);
    }

    @PostMapping("/login")
    public ResponseDto login(@RequestBody LoginUserDto loginUserDto){
        return userService.login(loginUserDto);
    }
}
