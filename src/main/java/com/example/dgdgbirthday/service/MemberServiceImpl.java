package com.example.dgdgbirthday.service;


import com.example.dgdgbirthday.domain.Member;
import com.example.dgdgbirthday.dto.MemberDto;
import com.example.dgdgbirthday.dto.member.MemberRequest;
import com.example.dgdgbirthday.dto.member.MemberResponse;
import com.example.dgdgbirthday.repository.MemberRepository;
import com.example.dgdgbirthday.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
Repository와 DTO를 통해 DB에 접근하여 CRUD의 각각의 프로세스 관리와 예외처리 등을 담당한다.

@Service 어노테이션을 붙이면 스프링에서 관리하는 객체가 된다.

Domain과 DTO를 나눈 것과 마찬가지로 Service와 Controller를 나눈 이유가 있는데

중복 코드가 생기기 때문이다.
중복 코드가 발생하면 모듈화를 통해 나눠주어 유지 보수를 하기 편하다.
기능을 세분화 하여 Service에 등록하면 추후 기능을 조합하기만 해서 새로운 기능을 만들 수 있다.
Service에서 다른 Service를 의존성 참조하는 것도 가능하다.
즉 Service를 사용하는 이유는 확장성, 재사용성, 중복 코드의 제거이다.
 */


@Service			// 내가 서비스다
@RequiredArgsConstructor    // 밑에 MemberRepository의 생성자를 쓰지 않기 위해서
@Slf4j
public class MemberServiceImpl implements MemberService{

    private  MemberRepository memberRepository;
    private  PasswordEncoder encoder;	// 추가
    private  TokenProvider tokenProvider;


    @Override
    public MemberResponse signIn(MemberRequest request) {
        Member member = memberRepository.findById(request.email())
                .filter(it -> encoder.matches(request.password(), it.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
        String token = tokenProvider.createToken(String.format("%s:%s", member.getId()));
        return new MemberResponse(member.getUsername(), token);
    }

    @Override
    public Member singUp(MemberDto memberDto) {
        Member member = new Member();
//        try {
//            memberRepository.flush();
//        } catch (DataIntegrityViolationException e) {
//            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
//        }
        return memberRepository.save(member);
    }

}
