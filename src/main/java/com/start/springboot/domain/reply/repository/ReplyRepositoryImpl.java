package com.start.springboot.domain.reply.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
}
