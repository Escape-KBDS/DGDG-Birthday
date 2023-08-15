package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.vo.request.JoinReq;
import com.example.dgdgbirthday.vo.request.LoginReq;
import com.example.dgdgbirthday.vo.response.JoinRes;
import com.example.dgdgbirthday.vo.response.LoginRes;

public interface UserService {
    JoinRes join(JoinReq joinReq);

    LoginRes login(LoginReq loginReq);

    void deleteRefreshToken(String userId);

}
