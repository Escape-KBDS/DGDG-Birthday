package com.example.dgdgbirthday.controller;

import com.example.dgdgbirthday.domain.Message;
import com.example.dgdgbirthday.domain.User;
import com.example.dgdgbirthday.repository.MessageRepository;
import com.example.dgdgbirthday.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public StatisticsController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/mbti")
    public ResponseEntity<Map<String, Integer>> getMbtiStatistics() {
        List<User> users = userRepository.findAll();
        Map<String, Integer> statistics = new HashMap<>();

        for (User user : users) {
            String mbti = user.getMbti();
            List<Message> messages = messageRepository.findByReceiver_Mbti(mbti);
            int messageCount = messages.size();
            statistics.put(mbti, messageCount);
        }

        return ResponseEntity.status(HttpStatus.OK).body(statistics);
    }
}