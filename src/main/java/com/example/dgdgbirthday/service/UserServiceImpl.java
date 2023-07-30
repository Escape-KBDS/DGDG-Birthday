package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.exception.InvalidPasswordException;
import com.example.dgdgbirthday.exception.UserNotFoundException;
import com.example.dgdgbirthday.repository.UserRepository;
import com.example.dgdgbirthday.security.TokenProvider;
import com.example.dgdgbirthday.vo.RequestLogin;
import com.example.dgdgbirthday.vo.ResponseLogin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final TokenProvider tokenProvider;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenProvider tokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signup(UserDto userDto) {
        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(userDto, User.class);
        user.setEncryptedPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ResponseLogin signin(RequestLogin requestLogin) {
        if (userRepository.findByEmail(requestLogin.getEmail()) == null) {
            throw new UserNotFoundException("해당하는 이메일 : " + requestLogin.getEmail() + "이 존재하지 않습니다.");
        }

        User user = userRepository.findByEmailAndPassword(requestLogin.getEmail(), requestLogin.getPassword());
        if(user == null){
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }

        ModelMapper mapper = new ModelMapper();
        ResponseLogin responseLogin = mapper.map(user, ResponseLogin.class);

        String token = tokenProvider.createToken(String.format("%s:%s", user.getEmail(), user.getUsername()));
        responseLogin.setToken(token);
        return responseLogin;
    }
}
