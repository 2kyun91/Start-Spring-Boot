package com.start.springboot.domain.attach.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.attach.entity.Attach;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.start.springboot.domain.attach.entity.QAttach.attach;

@Repository
@RequiredArgsConstructor
public class AttachRepositoryImpl implements AttachRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updateAttach(Attach attachObj) {
        return jpaQueryFactory.update(attach)
                .set(attach.attachPath, attachObj.getAttachPath())
                .where(attach.attachId.eq(attachObj.getAttachId()))
                .execute();
    }
}
