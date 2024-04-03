package com.ll.surl.domain.surl.surl.entity;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.domain.surl.surlComment.entity.SurlComment;
import com.ll.surl.global.app.AppConfig;
import com.ll.surl.global.jpa.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class Surl extends BaseTime {
    @ManyToOne
    private Member author;
    private String url;
    private String title;
    private String body;

    @OneToMany(mappedBy = "surl", cascade = ALL, orphanRemoval = true)
    @ToString.Exclude
    @Builder.Default
    @OrderBy("id DESC")
    private List<SurlComment> comments = new ArrayList<>();
    @Column(columnDefinition = "BIGINT default 0")
    @Setter(PROTECTED)
    private long commentsCount;

    public String getShortUrl() {
        return AppConfig.getSiteFrontUrl() + "/" + getId();
    }

    public SurlComment addComment(Member author, String body, boolean published) {
        SurlComment surlComment = SurlComment.builder()
                .surl(this)
                .author(author)
                .body(body)
                .published(published)
                .build();

        comments.add(surlComment);

        return surlComment;
    }

    public void deleteComment(SurlComment surlComment) {
        comments.remove(surlComment);
    }
}