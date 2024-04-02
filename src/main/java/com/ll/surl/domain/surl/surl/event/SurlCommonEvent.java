package com.ll.surl.domain.surl.surl.event;

import com.ll.surl.domain.surl.surl.dto.SurlDto;
import com.ll.surl.standard.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurlCommonEvent extends Event {
    private String eventType;
    private SurlDto surlDto;
}
