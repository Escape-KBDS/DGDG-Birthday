package com.example.dgdgbirthday.repository;

import com.example.dgdgbirthday.domain.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    MessageEntity findByMessageId(Long messageId);
}
