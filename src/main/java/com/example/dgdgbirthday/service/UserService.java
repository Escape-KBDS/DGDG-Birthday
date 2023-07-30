package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.vo.RequestLogin;
import com.example.dgdgbirthday.vo.ResponseLogin;

public interface UserService {
    User signup(UserDto userDto);
    ResponseLogin signin(RequestLogin requestLogin);
}