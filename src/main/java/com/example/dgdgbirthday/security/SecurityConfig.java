package com.example.dgdgbirthday.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;	// JwtAuthenticationFilter 주입
    private static final String[] AUTH_WHITELIST = {
            "/",
            "/user/**",
            "/swagger-ui/**",
            "/message/send/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/statistics/**",
            "/error",
            "/login"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(request -> request.disable())
                .headers(headers -> headers.frameOptions(frameOptionsConfig -> headers.disable()))	// H2 콘솔 사용을 위한 설정
                .authorizeHttpRequests(requests ->
                        requests.requestMatchers(AUTH_WHITELIST).permitAll()	// requestMatchers의 인자로 전달된 url은 모두에게 허용
                                .requestMatchers(PathRequest.toH2Console()).permitAll()	// H2 콘솔 접속은 모두에게 허용
                                .anyRequest().authenticated()	// 그 외의 모든 요청은 인증 필요
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ) // 세션을 사용하지 않으므로 STATELESS 설정
                .addFilterBefore(jwtAuthenticationFilter, BasicAuthenticationFilter.class) // JwtAuthenticationFilter를 주입 받아 BasicAuthenticationFilter 앞에 추가함.
//                .addFilter(jwtAuthenticationFilter) // JwtAuthenticationFilter를 주입 받아 BasicAuthenticationFilter 앞에 추가함.
                // 스프링 시큐리티 공식 문서에서 언급하는 필터 순서 : ... → BearerTokenAuthenticationFilter → BasicAuthenticationFilter → ...
                .build();
    }
}