package com.example.dgdgbirthday.repository;

import com.example.dgdgbirthday.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByPhonenum(String phonenum);
}
