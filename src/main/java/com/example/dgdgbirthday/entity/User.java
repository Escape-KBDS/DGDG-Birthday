package com.example.dgdgbirthday.entity;

import com.example.dgdgbirthday.common.MemberType;
import com.example.dgdgbirthday.vo.request.JoinReq;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Column(nullable = false, unique = true)
    private String phonenum;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String birthday;

    @Column
    private String mbti;

    @Enumerated(EnumType.STRING)
    private MemberType type;

    private LocalDateTime createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public static User from(JoinReq joinReq, PasswordEncoder passwordEncoder) {
        return User.builder()
                .phonenum(joinReq.phonenum())
                .pw(passwordEncoder.encode(joinReq.pw()))
                .name(joinReq.name())
                .birthday(joinReq.birthday())
                .mbti(joinReq.mbti())
                .type(MemberType.USER)
                .createdAt(LocalDateTime.now())
                .build();
    }

//    public void update(MemberUpdateRequest newMember, PasswordEncoder passwordEncoder) {
//        this.password = newMember.newPassword() == null || newMember.newPassword().isBlank()
//                ? this.password : passwordEncoder.encode(newMember.password());
//        this.name = newMember.name();
//        this.age = newMember.age();
//    }
}
