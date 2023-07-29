package com.example.dgdgbirthday.service;

import com.example.dgdgbirthday.domain.Message;
import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.dto.MessageDto;
import com.example.dgdgbirthday.exception.UserNotFoundException;
import com.example.dgdgbirthday.repository.MessageRepository;
import com.example.dgdgbirthday.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService{
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public MessageDto sendMessage(String email, String content) {
        User receiver = userRepository.findByEmail(email);
        if (receiver == null) {
            throw new UserNotFoundException("해당하는 이메일 : " + email + "이 존재하지 않습니다.");
        }
        Message message = new Message();
        message.setContent(content);
        message.setReceiver(receiver);
        messageRepository.save(message);

        MessageDto messageDto = new MessageDto();
        messageDto.setEmail(email);
        messageDto.setContent(content);
        return messageDto;
    }
}
