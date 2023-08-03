package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.Member;
import com.example.dgdgbirthday.dto.MemberDto;
import com.example.dgdgbirthday.dto.member.MemberRequest;
import com.example.dgdgbirthday.dto.member.MemberResponse;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    MemberResponse signIn(MemberRequest request);

    Member singUp(MemberDto memberDto);
}
