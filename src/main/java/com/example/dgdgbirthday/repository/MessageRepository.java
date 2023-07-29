package com.example.dgdgbirthday.repository;

import com.example.dgdgbirthday.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByReceiver_Mbti(String mbti);
}
