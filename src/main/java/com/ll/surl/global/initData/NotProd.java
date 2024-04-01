package com.ll.surl.global.initData;

import com.ll.surl.domain.member.member.entity.Member;
import com.ll.surl.domain.member.member.service.MemberService;
import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.domain.surl.surl.service.SurlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@Profile("!prod")
@Configuration
@RequiredArgsConstructor
@Slf4j
public class NotProd {
    @Autowired
    @Lazy
    private NotProd self;
    private final SurlService surlService;
    private final MemberService memberService;

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            if (surlService.count() > 0) return;

            self.work1();
        };
    }

    @Transactional
    public void work1() {
        Member memberSystem = memberService.create("system", "1234", "system");
        memberSystem.setRefreshToken("system");

        Member memberAdmin = memberService.create("admin", "1234", "admin");
        memberAdmin.setRefreshToken("admin");

        Member memberGarage = memberService.create("garage", "1234", "garage");
        memberGarage.setRefreshToken("garage");

        Member memberUser1 = memberService.create("user1", "1234", "user1");
        memberUser1.setRefreshToken("user1");

        Member memberUser2 = memberService.create("user2", "1234", "user2");
        memberUser2.setRefreshToken("user2");

        Member memberUser3 = memberService.create("user3", "1234", "user3");
        memberUser3.setRefreshToken("user3");

        Surl surl1 = surlService.create(memberUser1, "https://www.naver.com", "네이버", "네이버");
        Surl surl2 = surlService.create(memberUser1, "https://www.google.com", "구글", "구글");
        Surl surl3 = surlService.create(memberUser2, "https://www.daum.net", "다음", "다음");

        Surl surl4 = surlService.create(memberUser2, "https://zum.com", "줌", "줌");
        surlService.modify(surl4, "줌 인터넷", "줌 인터넷");

        Surl surl5 = surlService.create(memberUser3, "https://www.youtube.com", "유튜브", "유튜브");
        surlService.delete(surl5);
    }
}