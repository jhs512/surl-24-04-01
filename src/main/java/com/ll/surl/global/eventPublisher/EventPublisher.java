package com.ll.surl.global.eventPublisher;

import com.ll.surl.standard.event.Event;

public interface EventPublisher {
    void publish(Event afterCreated);
}
