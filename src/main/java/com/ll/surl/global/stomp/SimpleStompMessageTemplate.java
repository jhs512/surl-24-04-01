package com.ll.surl.global.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Profile("!rabbitmq")
@Component
@RequiredArgsConstructor
public class SimpleStompMessageTemplate implements StompMessageTemplate {
    private final SimpMessagingTemplate template;

    @Override
    public void convertAndSend(String type, String destination, Object payload) {
        template.convertAndSend("/" + type + "/" + destination, payload);
    }
}