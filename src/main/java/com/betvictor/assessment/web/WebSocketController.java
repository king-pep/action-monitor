package com.betvictor.assessment.web;


import com.betvictor.assessment.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WebSocketController {

    @Autowired
    UserService userService;

    @MessageMapping("/message/{toUser}")
    public Boolean sendMessage(
            Principal principal,
            @Header String authKey,
            @DestinationVariable String toUser,
            @RequestBody WebSocketRequestMessage message) {
        log.info("Send message from user {} to user {}. Auth key {}", principal.getName(), toUser, authKey);
        userService.notifyUser(toUser, message.getMessageContent());
        return Boolean.TRUE;
    }



    @Getter
    @Setter
    public static class WebSocketRequestMessage {
        private String messageContent;
    }

}
