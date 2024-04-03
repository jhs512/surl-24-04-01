package com.ll.surl.domain.surl.surlComment.entity;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Setter
public class SurlComment extends BaseTime {
    @ManyToOne(fetch = LAZY)
    private Surl surl;
    @ManyToOne(fetch = LAZY)
    private Member author;
    private boolean published;
    private String body;
}
