package com.example.dgdgbirthday.controller;

import com.example.dgdgbirthday.domain.UserEntity;
import com.example.dgdgbirthday.dto.MessageDto;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.service.UserService;
import com.example.dgdgbirthday.vo.ResponseMessage;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/join")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        UserEntity userEntity = UserEntity.builder()
//                                .phonenum(userDto.getPhonenum())
//                                .pw(userDto.getPw())
//                                .name(userDto.getName())
//                                .birthday(userDto.getBirthday())
//                                .mbti(userDto.getMbti())
//                                .build();

//        ResponseMessage responseMessage = mapper.map(userService.join(userDto), ResponseMessage.class);
        userService.join(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
