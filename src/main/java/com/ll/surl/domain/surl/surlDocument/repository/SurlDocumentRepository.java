package com.ll.surl.domain.surl.surlDocument.repository;

import com.ll.surl.domain.surl.surlDocument.document.SurlDocument;
import com.ll.surl.standard.dto.KwTypeV3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SurlDocumentRepository {
    void save(SurlDocument surlDocument);

    void clear();

    List<SurlDocument> findByOrderByIdDesc();

    Optional<SurlDocument> findById(long id);

    Page<SurlDocument> findByKw(KwTypeV3 kwType, String kw, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    void modify(SurlDocument surlDocument);

    void deleteById(long id);
}
