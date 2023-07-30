package com.example.dgdgbirthday.controller;

import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.service.UserService;
import com.example.dgdgbirthday.vo.RequestLogin;
import com.example.dgdgbirthday.vo.ResponseLogin;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Value("${secret-key}") String secretKey;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test(){
        System.out.println(secretKey);
        return "test";
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto) {
        User createdUser = userService.signup(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponseLogin> userLogin(@RequestBody RequestLogin requestLogin) {
        ResponseLogin responseLogin = userService.signin(requestLogin);
        return ResponseEntity.status(HttpStatus.OK).body(responseLogin);
    }

}
