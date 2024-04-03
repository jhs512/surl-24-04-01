package com.ll.surl.domain.surl.surlComment.dto;

import com.ll.surl.domain.surl.surlComment.entity.SurlComment;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
public class SurlCommentDto {
    @NonNull
    private long id;
    @NonNull
    private LocalDateTime createDate;
    @NonNull
    private LocalDateTime modifyDate;
    @NonNull
    private long authorId;
    @NonNull
    private String authorName;
    @NonNull
    private String authorProfileImgUrl;
    @NonNull
    private String body;

    @Setter
    private Boolean actorCanEdit;
    @Setter
    private Boolean actorCanDelete;
    @Setter
    private Boolean actorCanReply;

    public SurlCommentDto(SurlComment surlComment) {
        this.id = surlComment.getId();
        this.createDate = surlComment.getCreateDate();
        this.modifyDate = surlComment.getModifyDate();
        this.authorId = surlComment.getAuthor().getId();
        this.authorName = surlComment.getAuthor().getName();
        this.authorProfileImgUrl = surlComment.getAuthor().getProfileImgUrlOrDefault();
        this.body = surlComment.getBody();
    }
}
