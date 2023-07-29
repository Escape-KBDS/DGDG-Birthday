package com.example.dgdgbirthday.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Message")
@Data
public class Message {

    @Id @GeneratedValue
    private Long messageId;

    private String content;

    @CreationTimestamp
    private LocalDateTime writtenTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private User receiver;

    public void setUser(User receiver){
        this.receiver = receiver;
        receiver.getReceivedMessages().add(this);
    }
}
