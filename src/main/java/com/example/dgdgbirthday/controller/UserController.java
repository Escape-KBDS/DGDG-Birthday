package com.example.dgdgbirthday.controller;

import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.security.TokenProvider;
import com.example.dgdgbirthday.service.UserService;
import com.example.dgdgbirthday.vo.ApiResponse;
import com.example.dgdgbirthday.vo.request.JoinReq;
import com.example.dgdgbirthday.vo.request.LoginReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저 컨트롤러")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 가입")
    @PostMapping("/join")
    public ApiResponse join(@RequestBody JoinReq joinReq) {
        return ApiResponse.success(userService.join(joinReq));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginReq loginReq) {
        return ApiResponse.success(userService.login(loginReq));
    }

//    @GetMapping("/logout")
//    public ApiResponse logout(HttpServletRequest request){
//        return ApiResponse.success(userService.logout(request));
//    }
}
