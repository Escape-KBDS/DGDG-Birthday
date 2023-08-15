package com.example.dgdgbirthday.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginReq (
    @Schema(description = "핸드폰 번호", example = "01011112222")
    String phonenum,

    @Schema(description = "비밀번호", example = "1234")
    String pw
){}
