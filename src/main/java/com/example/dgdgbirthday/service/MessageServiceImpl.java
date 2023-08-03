//package com.example.dgdgbirthday.service;
//
//import com.example.dgdgbirthday.domain.MessageEntity;
//import com.example.dgdgbirthday.dto.MessageDto;
//import com.example.dgdgbirthday.repository.MessageRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MessageServiceImpl implements MessageService{
//    private MessageRepository messageRepository;
//
//    @Autowired
//    public MessageServiceImpl(MessageRepository messageRepository) {
//        this.messageRepository = messageRepository;
//    }
//
//
//    @Override
//    public MessageDto createMessage(MessageDto messageDto) {
//        ModelMapper mapper = new ModelMapper();
//        MessageEntity messageEntity = mapper.map(messageDto, MessageEntity.class);
//        messageRepository.save(messageEntity);
//        return messageDto;
//    }
//}
