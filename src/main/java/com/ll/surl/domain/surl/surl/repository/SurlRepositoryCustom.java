package com.ll.surl.domain.surl.surl.repository;

import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.standard.dto.KwTypeV3;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface SurlRepositoryCustom {
    Page<Surl> findByKw(KwTypeV3 kwType, String kw, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
