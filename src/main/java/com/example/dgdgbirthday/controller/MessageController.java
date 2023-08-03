//package com.example.dgdgbirthday.controller;
//
//import com.example.dgdgbirthday.dto.MessageDto;
//import com.example.dgdgbirthday.repository.MessageRepository;
//import com.example.dgdgbirthday.vo.RequestMessage;
//import com.example.dgdgbirthday.vo.ResponseMessage;
//import com.example.dgdgbirthday.service.MessageService;
//import lombok.extern.slf4j.Slf4j;
//import org.modelmapper.ModelMapper;
//import org.modelmapper.convention.MatchingStrategies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/message")
//@Slf4j
//public class MessageController {
//    private MessageService messageService;
//
//    @Autowired
//    public MessageController(MessageService messageService) {
//        this.messageService = messageService;
//    }
//
//    @PostMapping("/createMessage")
//    public ResponseEntity<ResponseMessage> createMessage (@RequestBody RequestMessage requestMessage){
//        ModelMapper mapper = new ModelMapper();
//        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        MessageDto messageDto = mapper.map(requestMessage, MessageDto.class);
//        log.info(requestMessage+"");
//        log.info(requestMessage.toString()+"");
//        log.info(messageDto+"");
//        log.info(messageDto.toString()+"");
//
//        ResponseMessage responseMessage = mapper.map(messageService.createMessage(messageDto), ResponseMessage.class);
//        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
//    }
//}
