package com.ll.surl.domain.surl.surl.entity;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.global.app.AppConfig;
import com.ll.surl.global.jpa.entity.BaseTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;
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

    public String getShortUrl() {
        return AppConfig.getSiteBackUrl() + "/" + getId();
    }
}