package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;

public interface UserService {
    User createUser(UserDto userDto);
}