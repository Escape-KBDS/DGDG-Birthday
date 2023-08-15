package com.example.dgdgbirthday.vo.response;

import com.example.dgdgbirthday.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record JoinRes (
    @Schema(description = "회원 고유키", example = "c0a80121-7aeb-4b4b-8b7a-9b9b9b9b9b9b")
    UUID id,

    @Schema(description = "핸드폰 번호", example = "01011112222")
    String phonenum,

    @Schema(description = "이름", example = "김지현")
    String name,

    @Schema(description = "생일", example = "19960323")
    String birthday,

    @Schema(description = "mbti", example = "ESTJ")
    String mbti
){
    public static JoinRes from(User user){
        return new JoinRes(
                user.getId(),
                user.getPhonenum(),
                user.getName(),
                user.getBirthday(),
                user.getMbti()
        );
    }
}
