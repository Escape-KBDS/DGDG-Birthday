package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.entity.User;
import com.example.dgdgbirthday.dto.UserDto;
import com.example.dgdgbirthday.repository.UserRepository;
import com.example.dgdgbirthday.security.TokenProvider;
import com.example.dgdgbirthday.vo.request.JoinReq;
import com.example.dgdgbirthday.vo.request.LoginReq;
import com.example.dgdgbirthday.vo.response.JoinRes;
import com.example.dgdgbirthday.vo.response.LoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RedisService redisService;

    @Transactional
    @Override
    public JoinRes join(JoinReq joinReq) {
        User user = userRepository.save(User.from(joinReq,passwordEncoder));
        try {
            userRepository.flush();
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("이미 사용중인 아이디입니다.");
        }
        return JoinRes.from(user);
    }

    @Transactional(readOnly = true)
    @Override
    public LoginRes login(LoginReq loginReq) {
        User user = userRepository.findByPhonenum(loginReq.phonenum())
                .filter(it -> passwordEncoder.matches(loginReq.pw(), it.getPw()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));
        String token = tokenProvider.createToken(String.format("%s:%s", user.getId(), user.getType()));
        return new LoginRes(user.getPhonenum(), user.getType(),token);
    }

    @Override
    public void deleteRefreshToken(String userId) {
        redisService.deleteData(userId);
    }
}
