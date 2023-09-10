package com.start.springboot.domain.attach.repository;

import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.post.dto.PostBoardDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.start.springboot.domain.attach.entity.QAttach.attach;

@Repository
@RequiredArgsConstructor
public class AttachRepositoryImpl implements AttachRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updateAttach(Attach attachObj) {
        return jpaQueryFactory.update(attach)
                .set(attach.attachPath, attachObj.getAttachPath())
                .where(attach.attachId.eq(attachObj.getAttachId()))
                .execute();
    }

    @Override
    public List<Attach> getAttachList(Long postId) {
        return jpaQueryFactory.selectFrom(attach)
                .where(attach.post.postId.eq(postId))
                .fetch();
    }

    @Override
    public void deleteAttachList(PostBoardDto postBoardDto) {
        JPADeleteClause deleteQuery = jpaQueryFactory.delete(attach).where(attach.post.postId.eq(postBoardDto.getPostId()));
        if (ObjectUtils.isNotEmpty(postBoardDto.getOriginalAttachId())) {
            deleteQuery.where(attach.attachId.notIn(postBoardDto.getOriginalAttachId()));
        }
        deleteQuery.execute();
    }
}
