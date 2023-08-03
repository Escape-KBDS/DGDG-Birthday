package com.example.dgdgbirthday.dto;

import lombok.Data;

/*
DTO는 말 그대로 Data Transfer Object로 "데이터 전송 객체"이다.
Service나 Controller에서 DB에 접근할 때 사용하는 클래스로 Domain과 차이점이 있는데
Domain은 DB 테이블에 대한 정보를 가지고 있는 클래스이고, DTO는 해당 테이블에서 실제로
CRUD를 할 필드를 정의해둔 것이라고 보면 된다. 따라서 테이블에 대한 정보를 작성하는
Domain 클래스와 DB에 접근하는 필드에 관한 내용을 작성하는 DTO 클래스를 사용하며
Domain과 마찬가지로 Builder 패턴을 사용할 수 있습니다.
 */


@Data
public class MemberDto {

    private String email;
    private String username;
    private String password;
    private String mbti;

}
