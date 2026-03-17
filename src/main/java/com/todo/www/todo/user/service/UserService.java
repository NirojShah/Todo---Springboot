package com.todo.www.todo.user.service;

import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.user.dto.CreateUserDto;
import com.todo.www.todo.user.dto.LoginUserDto;
import com.todo.www.todo.user.dto.UpdaetUserDto;

public interface UserService {
    ResponseDto signUp(CreateUserDto createUserDto);
    ResponseDto login(LoginUserDto loginUserDto);
    ResponseDto updateProfile(UpdaetUserDto updaetUserDto, int userId);
}
