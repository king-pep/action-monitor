package com.betvictor.assessment.config;

import com.betvictor.assessment.annotation.Generated;
import com.betvictor.assessment.handler.WebSocketConnectHandler;
import com.betvictor.assessment.handler.WebSocketDisconnectHandler;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

@Configuration
@Generated
public class WebSocketHandlersConfig<S extends Session> {

    @Bean
    public WebSocketConnectHandler<S> webSocketConnectHandler(SimpMessageSendingOperations messagingTemplate) {
        return new WebSocketConnectHandler<>(messagingTemplate);
    }

    @Bean
    public WebSocketDisconnectHandler<S> webSocketDisconnectHandler(SimpMessageSendingOperations messagingTemplate) {
        return new WebSocketDisconnectHandler<>(messagingTemplate);
    }

}
