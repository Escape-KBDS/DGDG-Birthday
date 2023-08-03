package com.example.dgdgbirthday.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDto {
    private String phonenum;
    private String pw;
    private String name;
    private String birthday;
    private String mbti;
}
