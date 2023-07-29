package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.Message;
import com.example.dgdgbirthday.dto.MessageDto;

public interface MessageService {
    MessageDto sendMessage(String email, String content);
}
