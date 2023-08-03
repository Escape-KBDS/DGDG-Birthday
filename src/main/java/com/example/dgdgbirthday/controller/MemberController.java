package com.example.dgdgbirthday.controller;


import com.example.dgdgbirthday.domain.Member;
import com.example.dgdgbirthday.dto.MemberDto;
import com.example.dgdgbirthday.dto.member.MemberRequest;
import com.example.dgdgbirthday.dto.member.MemberResponse;
import com.example.dgdgbirthday.service.MemberService;

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
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Value("${secret-key}") String secretKey;


    @GetMapping("/test")
    public String test(){
        System.out.println(secretKey);
        return "test";
    }

    @PostMapping("/signUp")
    public ResponseEntity<Member> signUp(@RequestBody MemberDto memberDto) {
        log.info("memberDto :: " + memberDto);
        Member member = memberService.singUp(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }

    @PostMapping("/signIn")
    public ResponseEntity<MemberResponse> signIn(@RequestBody MemberRequest request) {
        MemberResponse memberResponse = memberService.signIn(request);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

}
