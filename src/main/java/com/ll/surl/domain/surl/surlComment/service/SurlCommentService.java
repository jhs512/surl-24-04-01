package com.ll.surl.domain.surl.surlComment.service;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surlComment.entity.SurlComment;
import com.ll.surl.domain.surl.surlComment.repository.SurlCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SurlCommentService {
    private final SurlCommentRepository surlCommentRepository;


    @Transactional
    public void delete(Surl surl, SurlComment surlComment) {
        surl.deleteComment(surlComment);
    }

    @Transactional
    public SurlComment write(Member author, Surl surl, String body, boolean published) {
        SurlComment surlComment = surl.addComment(author, body, published);

        return surlComment;
    }

    @Transactional
    public void edit(Surl surl, SurlComment surlComment, String body) {
        surlComment.setBody(body);
    }

    public List<SurlComment> findBySurlAndPublishedOrderByIdDesc(Surl surl, boolean published) {
        return surlCommentRepository.findBySurlAndPublishedOrderByIdDesc(surl, true);
    }

    public Optional<SurlComment> findById(long id) {
        return surlCommentRepository.findById(id);
    }
}
