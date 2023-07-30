package com.example.dgdgbirthday.vo;

import lombok.Data;

@Data
public class ResponseLogin {
    private String email;
    private String username;
    private String birthday;
    private String mbti;
    private String token;
}
