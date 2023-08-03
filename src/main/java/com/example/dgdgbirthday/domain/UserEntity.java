package com.example.dgdgbirthday.domain;

import com.example.dgdgbirthday.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserEntity {
    private UserEntity(){}

    @Id
    private String phonenum;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthday;

    @Column
    private String mbti;
}
