package com.example.dgdgbirthday.vo.response;

import com.example.dgdgbirthday.common.MemberType;
import io.swagger.v3.oas.annotations.media.Schema;

public record LoginRes (
    @Schema(description = "이름", example = "김지현")
    String name,
    @Schema(description = "회원 유형", example = "USER")
    MemberType type,
    String token
){}
