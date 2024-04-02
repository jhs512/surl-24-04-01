package com.ll.surl.global.eventPublisher;

import com.ll.surl.standard.event.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Profile("kafka")
@Component
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {
    private final KafkaTemplate<Object, Object> template;

    @Override
    public void publish(Event event) {
        template.send(event.getName(), event);
    }
}
