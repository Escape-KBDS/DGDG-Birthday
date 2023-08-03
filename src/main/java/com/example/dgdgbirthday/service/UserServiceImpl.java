package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.MessageEntity;
import com.example.dgdgbirthday.domain.UserEntity;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) { this.userRepository = userRepository; }

    @Override
    public UserDto join(UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userRepository.save(userEntity);
        return userDto;
    }
}
