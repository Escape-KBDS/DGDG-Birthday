package com.example.dgdgbirthday.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Order(0) // 의존성 주입 우선순위 설정
@RequiredArgsConstructor
@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { // 인증정보 설정
//        log.info("doFilterInternal 호출");
        if(isAuthenticationUnRequired(request)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = parseBearerToken(request); // HTTP 요청에서 토큰을 파싱
        if (token == null) { // 인증이 필요한 API에 토큰이 없으면 인증 거부
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        User user = parseUserSpecification(token); // 토큰 정보를 기반으로 User 객체 생성
        AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities()); // UsernamePasswordAuthenticationToken 객체 생성
        authenticated.setDetails(new WebAuthenticationDetails(request)); // 요청을 날린 클라이언트 또는 프록시의 IP 주소와 세션 ID를 저장. 없어도 상관 없음.
        SecurityContextHolder.getContext().setAuthentication(authenticated); // 시큐리티 컨텍스트의 인증 정보를 새로 생성한 인증 토큰으로 설정

        filterChain.doFilter(request, response); // 필터설정 끝 다음단계로 이동.
    }
 
    private String parseBearerToken(HttpServletRequest request) { // HTTP 요청에서 토큰 반환해주는 메서드
//        log.info("parseBearerToken 호출");
        return Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0, 7).equalsIgnoreCase("Bearer ")) // HTTP요청 헤더에 Authorization 값을 찾아 Bearer로 시작하는지 확인
                .map(token -> token.substring(7)) // 접두어를 제외한 토큰값으로 파싱
                .orElse(null); // 헤더에 Authorization이 존재하지 않거나 접두어가 Bearer가 아니면 null 반환
    }
 
    private User parseUserSpecification(String token) {
//        log.info("parseUserSpecification 호출");
        String[] split = Optional.ofNullable(token) // 파싱된 토큰이 null이 아니면서
                .filter(subject -> subject.length() >= 10) // 길이가 너무 짧지 않을 때만
                .map(tokenProvider::validateTokenAndGetSubject) // 토큰을 복호화
                .orElse("anonymous:anonymous")
                .split(":");
 
        return new User(split[0], "", List.of(new SimpleGrantedAuthority(split[1]))); // 비밀번호는 로그인 API를 호출할 때 이미 확인을 했기 때문에, User 객체를 생성할 때는 사용하지 않음
    }

    private boolean isAuthenticationUnRequired(HttpServletRequest request) {
//        log.info("isAuthenticationRequired 호출");
        String requestURI = request.getRequestURI();
        String[] AUTH_WHITELIST = {
                "/user/",
                "/swagger-ui/",
                "/h2-console",
                "/message/send/",
                "/statistics/",
                "/swagger-resources/",
                "/v3/api-docs",
                "/error",
                "/login"
        };
        for (String pattern : AUTH_WHITELIST) {
            if (requestURI.startsWith(pattern)) {
                return true;
            }
        }
        return false;
    }
}