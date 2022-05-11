package com.betvictor.assessment.service;

import com.betvictor.assessment.domain.User;
import com.betvictor.assessment.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;


    public void notifyUser(final String userId, final String message) {
        this.send(userId, message);
    }

    @SneakyThrows
    private void send(String userId, String message) {
        String json = (new ObjectMapper()).writeValueAsString(new WebSocketResponseMessage(message));
        messagingTemplate.convertAndSendToUser(userId, "/topic/messages", json);
        userRepository.save(new User(userId, message, new Timestamp(System.currentTimeMillis())));

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WebSocketResponseMessage {

        private String content;

    }

}
