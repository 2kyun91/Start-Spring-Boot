package com.start.springboot.domain.user.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.start.springboot.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<UserDto> findByIdContains(String userId) {
        return jpaQueryFactory
                .select(Projections.fields(UserDto.class, user.userId, user.userName, user.userEmail, user.userCountCreateDate))
                .from(user)
                .where(user.userId.contains(userId))
//                .orderBy(user.userId.asc())
                .orderBy(
                        new CaseBuilder()
                                .when(user.userId.like("testUser%"))
                                .then(user.userId.substring(8).castToNum(Integer.class))
                                .otherwise(0)
                                .asc()
                )
                .fetch();
    }
}
