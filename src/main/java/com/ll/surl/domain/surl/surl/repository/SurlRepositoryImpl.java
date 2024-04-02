package com.ll.surl.domain.surl.surl.repository;

import com.ll.surl.domain.surl.surl.entity.Surl;
import com.ll.surl.standard.dto.KwTypeV3;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static com.ll.surl.domain.surl.surl.entity.QSurl.surl;

@Profile("!prod")
@Repository
@RequiredArgsConstructor
public class SurlRepositoryImpl implements SurlRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Surl> findByKw(KwTypeV3 kwType, String kw, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (startDate != null && endDate != null) {
            builder.and(surl.createDate.between(startDate, endDate));
        }

        if (kw != null && !kw.isBlank()) {
            applyKeywordFilter(kwType, kw, builder);
        }

        JPAQuery<Surl> postsQuery = createSurlsQuery(builder);
        applySorting(pageable, postsQuery);

        postsQuery.offset(pageable.getOffset()).limit(pageable.getPageSize());

        JPAQuery<Long> totalQuery = createTotalQuery(builder);

        return PageableExecutionUtils.getPage(postsQuery.fetch(), pageable, totalQuery::fetchOne);
    }

    private JPAQuery<Long> createTotalQuery(BooleanBuilder builder) {
        return jpaQueryFactory
                .select(surl.count())
                .from(surl)
                .where(builder);
    }

    private void applyKeywordFilter(KwTypeV3 kwType, String kw, BooleanBuilder builder) {
        builder.and(
                surl.title.containsIgnoreCase(kw)
                        .or(surl.body.containsIgnoreCase(kw))
                        .or(surl.url.containsIgnoreCase(kw))
        );
    }

    private JPAQuery<Surl> createSurlsQuery(BooleanBuilder builder) {
        return jpaQueryFactory
                .selectFrom(surl)
                .where(builder);
    }

    private void applySorting(Pageable pageable, JPAQuery<Surl> postsQuery) {
        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(surl.getType(), surl.getMetadata());
            String property = o.getProperty();
            if (property.equals("rating")) property = "id"; // QueryDSL 에는 rating 이 없으니, id 로 대체
            postsQuery.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC, pathBuilder.get(property)));
        }
    }
}