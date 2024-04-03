package com.ll.surl.domain.surl.surlComment.repository;

import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surlComment.entity.SurlComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurlCommentRepository extends JpaRepository<SurlComment, Long> {
    List<SurlComment> findBySurlAndPublishedOrderByIdDesc(Surl surl, boolean published);
}
