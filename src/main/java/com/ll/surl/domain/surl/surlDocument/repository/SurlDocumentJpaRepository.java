package com.ll.surl.domain.surl.surlDocument.repository;

import com.ll.surl.domain.surl.surl.dto.SurlDto;
import com.ll.surl.domain.surl.surl.repository.SurlRepository;
import com.ll.surl.domain.surl.surlDocument.document.SurlDocument;
import com.ll.surl.standard.dto.KwTypeV3;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Profile("!meilisearch")
@Repository
@RequiredArgsConstructor
public class SurlDocumentJpaRepository implements SurlDocumentRepository {
    private final SurlRepository surlRepository;

    @Override
    public void save(SurlDocument surlDocument) {
        // 구현할 필요 없음
    }

    @Override
    public void clear() {
        // 구현할 필요 없음
    }

    @Override
    public List<SurlDocument> findByOrderByIdDesc() {
        return surlRepository
                .findByOrderByIdDesc()
                .stream()
                .map(SurlDto::new)
                .map(SurlDocument::new)
                .toList();
    }

    @Override
    public Optional<SurlDocument> findById(long id) {
        return surlRepository
                .findById(id)
                .map(SurlDto::new)
                .map(SurlDocument::new);
    }

    @Override
    public Page<SurlDocument> findByKw(KwTypeV3 kwType, String kw, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        return surlRepository
                .findByKw(kwType, kw, startDate, endDate, pageable)
                .map(SurlDto::new)
                .map(SurlDocument::new);
    }

    @Override
    public void modify(SurlDocument surlDocument) {
        // 구현할 필요 없음
    }

    @Override
    public void deleteById(long id) {
        // 구현할 필요 없음
    }
}
