package org.webshop.common.utils;

import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.jpa.JPQLQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Service
public class QueryDSLUtils {

    private final EntityManager entityManager;

    public <T> Page<T> getPaginated(final JPQLQuery<T> jpaQuery, final Pageable pageable, final Class<T> c) {
        final Querydsl querydsl = new Querydsl(entityManager, (new PathBuilderFactory()).create(c));
        querydsl.applyPagination(pageable, jpaQuery);

        return PageableExecutionUtils.getPage(jpaQuery.fetch(), pageable, jpaQuery::fetchCount);
    }
}
