package com.betvictor.assessment.service;

import com.betvictor.assessment.domain.User;
import com.betvictor.assessment.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserRepository userRepository;


    public void notifyUser(final String userId, final String message) {
        this.send(userId, message);
    }

    public User updateUser(final String userId, final String message) {
        log.info("updateUser: userId: {}, message: {}", userId, message);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setMessage(message);
        user.setTimestamp(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
        return user;
    }
    @SneakyThrows
    private void send(String userId, String message) {
log.info("send: userId: {}, message: {}", userId, message);
        if(userId != null && message != null) {
            String json = (new ObjectMapper()).writeValueAsString(new WebSocketResponseMessage(message));
            messagingTemplate.convertAndSendToUser(userId, "/topic/messages", json);
            userRepository.save(new User(userId, message, new Timestamp(System.currentTimeMillis())));
            log.info("a row with userId: {} and timestamp: {} was inserted", userId, new Timestamp(System.currentTimeMillis()));
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class WebSocketResponseMessage {

        private String content;

    }

}
