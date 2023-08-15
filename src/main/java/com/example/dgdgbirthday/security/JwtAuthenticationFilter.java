package com.example.dgdgbirthday.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

@Order(0)//int 범위 내에서 의존성 주입 우선순위 정하기.
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    //인증 정보 설정. HTTP 요청 헤더의 토큰을 기반으로 생성한 User 객체를 토대로 스프링 시큐리티에서 사용할 UsernamePasswordAuthenticationToken 객체를 생성한다.
    //이후 스프링 시큐리티 컨텍스트의 인증 정보를 새로 생성한 인증 토큰으로 설정하고 다음 필터로 넘어간다.
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(req);
        User user = parseUserSpecification(token);

        AbstractAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(user, token, user.getAuthorities());
        authenticated.setDetails(new WebAuthenticationDetails(req));
        SecurityContextHolder.getContext().setAuthentication(authenticated);

        filterChain.doFilter(req, res);
    }

    //http요청의 헤더에서 Authorization값을 찾아서 Bearer로 시작하는지 확인하고 접두어를 제외한 토큰값으로 파싱
    private String parseBearerToken(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(token -> token.substring(0,7).equalsIgnoreCase("Bearer "))
                .map(token -> token.substring(7))
                .orElse(null);
    }

    //토큰값을 토대로 토큰에 담긴 회원ID와 회원타입을 토대로 스프링 시큐리티에서 사용할 User객체를 반환
    private User parseUserSpecification(String token) {
        String[] split = Optional.ofNullable(token)//토큰이 null이 아니고
                .filter(subject -> subject.length() >= 10)//길이가 너무 짧지 않을때만 파싱
                .map(tokenProvider::validateTokenAndGetSubject)
                .orElse("anonymous:anonymous")//익명임을 뜻하는 유저객체 생성
                .split(":");

        //비밀번호는 로그인api호출시 확인했으므로 유저 객체를 생성할때는 빈 문자열로 넘김
        return new User(split[0],"", List.of(new SimpleGrantedAuthority(split[1])));
    }

}
