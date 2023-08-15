package com.example.dgdgbirthday.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record JoinReq (
    @Schema(description = "핸드폰 번호", example = "01011112222")
    String phonenum,

    @Schema(description = "비밀번호", example = "1234")
    String pw,

    @Schema(description = "이름", example = "김지현")
    String name,

    @Schema(description = "생일", example = "19960323")
    String birthday,

    @Schema(description = "mbti", example = "ESTJ")
    String mbti
){}
