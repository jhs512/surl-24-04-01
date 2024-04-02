package com.ll.surl.domain.surl.surlDocument.eventListener;

import com.ll.surl.domain.surl.surl.event.SurlCommonEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Profile("kafka")
@Component
@RequiredArgsConstructor
public class SurlKafkaEventListener {
    private final SurlEventHandler surlEventHandler;

    @KafkaListener(topics = "SurlCommonEvent", groupId = "1")
    public void consumeSurlCommonEvent(SurlCommonEvent event) {
        surlEventHandler.handle(event);
    }

    @KafkaListener(topics = "SurlCommonEvent.DLT", groupId = "1")
    public void consumeSurlCommonEventDLT(byte[] in) {
        String message = new String(in);
        System.out.println("SurlCommonEvent.DLT: failed message: " + message);
    }
}
