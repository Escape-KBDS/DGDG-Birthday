package com.example.dgdgbirthday.controller;

import com.example.dgdgbirthday.domain.Message;
import com.example.dgdgbirthday.dto.MessageDto;
import com.example.dgdgbirthday.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/test")
    public String test(){
        return "잘되낭";
    }

    @PostMapping("/send/{email}")
    public ResponseEntity<MessageDto> sendMessageToUser(@PathVariable String email, String content) {
        log.info("sendMessageToUser 호출");
        MessageDto messageDto = messageService.sendMessage(email, content);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDto);
    }

    @GetMapping("/createLink")
    public ResponseEntity<String> generateMessageLink(@RequestParam String receiverEmail) { // receiverEmail 는 front 에서..
        String link = "/messages/createLink?receiverEmail=" + receiverEmail;
        return ResponseEntity.ok(link);
    }
}
