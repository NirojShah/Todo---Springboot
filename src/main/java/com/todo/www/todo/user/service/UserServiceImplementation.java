package com.todo.www.todo.user.service;


import com.todo.www.todo.dto.ResponseDto;
import com.todo.www.todo.user.UserEntity;
import com.todo.www.todo.user.dto.CreateUserDto;
import com.todo.www.todo.user.dto.LoginUserDto;
import com.todo.www.todo.user.dto.UpdaetUserDto;
import com.todo.www.todo.user.modelmapper.ModelMapperConfig;
import com.todo.www.todo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapperConfig;

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

        modelMapperConfig.map(updatetUserDto,user);

        UserEntity updateedUser = userRepository.save(user);

        return new ResponseDto("success",HttpStatus.OK,null);
    }

    @Override
    public ResponseDto deleteProfile(int userId) {
        Optional<UserEntity> isPresent = userRepository.findById(userId);
        if(isPresent.isEmpty()){
            Map<String,String> response = new HashMap<>();
            response.put("message","User not found");
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,response);
        }
        UserEntity user = isPresent.get();
        userRepository.delete(user);
        return new ResponseDto("success",HttpStatus.ACCEPTED,null);

    }

    @Override
    public ResponseDto allUser() {
        List<UserEntity> users = userRepository.findAll();
        if(users.isEmpty()){
            return new ResponseDto("success",HttpStatus.NO_CONTENT,null);
        }
        return new ResponseDto("success",HttpStatus.FOUND,users);
    }

    @Override
    public ResponseDto userDetails(int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return new ResponseDto("failed",HttpStatus.NOT_FOUND,null);
        }

        UserEntity userInfo = user.get();
        return new ResponseDto("success", HttpStatus.FOUND,userInfo);
    }
}
