package com.ll.surl.domain.surl.surlDocument.eventListener;

import com.ll.surl.domain.surl.surl.event.SurlCommonEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Profile("!kafka")
@Component
@RequiredArgsConstructor
public class SurlSimpleEventListener {
    private final SurlEventHandler surlEventHandler;

    @EventListener
    public void handleEvent(SurlCommonEvent event) {
        surlEventHandler.handle(event);
    }
}
