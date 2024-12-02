package com.example.userLogin.service;

import com.example.userLogin.dto.request.UserSignupRequestDto;
import com.example.userLogin.dto.request.UserLoginRequestDto;
import com.example.userLogin.dto.response.UserResponseDto;

public interface UserService {
    void signup(UserSignupRequestDto userSignupRequestDto);
    UserResponseDto login(UserLoginRequestDto userLoginRequestDto);
}