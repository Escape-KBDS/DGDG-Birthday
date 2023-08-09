package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.dto.user.UserRequest;
import com.example.dgdgbirthday.dto.user.UserResponse;

public interface UserService {

    UserResponse signIn(UserRequest request);

    User signUp(UserDto userDto);
}
