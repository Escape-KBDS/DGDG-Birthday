package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
//        user.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
