package com.example.dgdgbirthday.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
@Data
public class User {
    @Id
    private String email;

    private String username;

    private String password;

    private String encryptedPassword;

    private String birthday;

    private String mbti;

    @CreationTimestamp
    private LocalDate joinDate;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Message> receivedMessages = new ArrayList<>();
}
