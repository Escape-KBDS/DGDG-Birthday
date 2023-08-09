package com.example.dgdgbirthday.controller;


import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.dto.user.UserRequest;
import com.example.dgdgbirthday.dto.user.UserResponse;
import com.example.dgdgbirthday.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/*
Http 요청과 응답을 위한 클래스로서 제일 앞단이라고 볼 수 있다.

@Controller 어노테이션을 통하여 bean에 등록되고 스프링에서 관리됩니다.

 */

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Value("${secret-key}") String secretKey;


    @GetMapping("/test")
    public String test(){
        System.out.println(secretKey);
        return "test end";
    }

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody UserDto userDto) {
        log.info("userDto :: " + userDto);
        User user = userService.signUp(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/signIn")
    public ResponseEntity<UserResponse> signIn(@RequestBody UserRequest request) {
        log.info("***********************");
        log.info("signIn request:: " + request);
        log.info("***********************");
        UserResponse userResponse = userService.signIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(userResponse);
    }

}
