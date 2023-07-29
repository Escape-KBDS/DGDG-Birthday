package com.example.dgdgbirthday.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "USER")
public class UserEntity {
    @Id
    private String email;

    private String password;

    private String encryptedPassword;

    private String birthday;

    @CreationTimestamp
    private LocalDate joinDate;
}
