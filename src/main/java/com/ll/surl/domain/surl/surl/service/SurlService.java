package com.ll.surl.domain.surl.surl.service;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.domain.surl.surl.dto.SurlDto;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surl.event.SurlCommonEvent;
import com.ll.surl.domain.surl.surl.repository.SurlRepository;
import com.ll.surl.global.eventPublisher.EventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurlService {
    private final SurlRepository surlRepository;
    private final EventPublisher eventPublisher;

    public long count() {
        return surlRepository.count();
    }

    @Transactional
    public Surl create(Member author, String url, String title, String body) {
        Surl surl = Surl.builder()
                .author(author)
                .url(url)
                .title(title)
                .body(body)
                .build();

        surlRepository.save(surl);

        eventPublisher.publish(new SurlCommonEvent("afterCreated", new SurlDto(surl)));

        return surl;
    }

    public Optional<Surl> findById(long id) {
        return surlRepository.findById(id);
    }

    @Transactional
    public void modify(Surl surl, String title, String body) {
        surl.setTitle(title);
        surl.setBody(body);
        surl.setModified();

        eventPublisher.publish(new SurlCommonEvent("afterModified", new SurlDto(surl)));
    }

    @Transactional
    public void delete(Surl surl) {
        eventPublisher.publish(new SurlCommonEvent("beforeDeleted", new SurlDto(surl)));

        surlRepository.delete(surl);
    }
}