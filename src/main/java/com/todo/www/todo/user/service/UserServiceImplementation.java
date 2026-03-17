package com.todo.www.todo.user.service;


import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.user.UserEntity;
import com.todo.www.todo.user.dto.CreateUserDto;
import com.todo.www.todo.user.dto.LoginUserDto;
import com.todo.www.todo.user.dto.UpdaetUserDto;
import com.todo.www.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDto signUp(CreateUserDto createUserDto) {
        Optional<UserEntity> isExists = Optional.ofNullable(userRepository.findByEmail(createUserDto.getEmail()));

        if(isExists.isPresent()){
            return new ResponseDto("failed",HttpStatus.ALREADY_REPORTED,null);
        }

        UserEntity user = new UserEntity();
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        user.setPhoneNo(createUserDto.getPhone());
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());

        UserEntity newUser = userRepository.save(user);

        return new ResponseDto("User created successfully", HttpStatus.CREATED, newUser);
    }

    @Override
    public ResponseDto login(LoginUserDto loginUserDto) {
        Optional<UserEntity> userInfo = Optional.ofNullable(userRepository.findByEmail(loginUserDto.getEmail()));
        if(userInfo.isEmpty()){
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,null);
        }
        return new ResponseDto("success",HttpStatus.OK,null);
    }

    @Override
    public ResponseDto updateProfile(UpdaetUserDto updatetUserDto, int userId) {
        Optional<UserEntity> isPresent = userRepository.findById(userId);
        if(isPresent.isEmpty()){
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,null);
        }
        UserEntity user = isPresent.get();
        if(updatetUserDto.getFirstName() != null){
            user.setFirstName(updatetUserDto.getFirstName());
        }
        if(updatetUserDto.getLastName() != null){
            user.setLastName(updatetUserDto.getLastName());
        }
        if(updatetUserDto.getEmail() != null){
            user.setEmail(updatetUserDto.getEmail());
        }
        if(updatetUserDto.getPassword() != null) {
            user.setPassword(updatetUserDto.getPassword());
        }
        if(updatetUserDto.getPhoneNo() != null){
            user.setPhoneNo(updatetUserDto.getPhoneNo());
        }

        UserEntity updateedUser = userRepository.save(user);

        return new ResponseDto("success",HttpStatus.OK,null);
    }
}
