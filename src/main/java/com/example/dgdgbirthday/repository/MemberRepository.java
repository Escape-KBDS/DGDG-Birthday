package com.example.dgdgbirthday.repository;

import com.example.dgdgbirthday.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
repository는 DB에 접근하는 소스코드를 모아둔 Interface다.

JpaRepository interface를 상속받아서 관리하고자 하는 클래스, ID 필드 타입을 JpaRepository<Entity Class, PK type>

같이 넣어주면 자동으로 DB와 CRUD 연결을 할 수 있는 메소드를 생성해준다.
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findById(String id);

}
