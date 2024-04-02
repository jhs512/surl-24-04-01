package com.ll.surl.domain.surl.surlDocument.eventListener;

import com.ll.surl.domain.surl.surl.event.SurlCommonEvent;
import com.ll.surl.domain.surl.surlDocument.service.SurlDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurlEventHandler {
    private final SurlDocumentService surlDocumentService;

    public void handle(SurlCommonEvent event) {
        switch (event.getEventType()) {
            case "afterCreated" -> surlDocumentService.add(event.getSurlDto());
            case "afterModified" -> surlDocumentService.modify(event.getSurlDto());
            case "beforeDeleted" -> surlDocumentService.delete(event.getSurlDto());
        }
    }
}
