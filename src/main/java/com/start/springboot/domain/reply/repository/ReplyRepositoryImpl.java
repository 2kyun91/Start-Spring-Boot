package com.start.springboot.domain.reply.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.reply.dto.ReplyDto;
import com.start.springboot.domain.reply.entity.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.start.springboot.domain.reply.entity.QReply.reply;

@Repository
@RequiredArgsConstructor
public class ReplyRepositoryImpl implements ReplyRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReplyDto> getReplies(Long postId) {
        List<ReplyDto> replyDtoList = jpaQueryFactory
                .select(
                        Projections.fields(
                                ReplyDto.class,
                                reply.replyId,
                                reply.replyWriter,
                                reply.replyContent,
                                reply.replyUpdateDate,
                                reply.replyShowYn
                        )
                ).from(reply)
                .where(reply.post.postId.eq(postId)).fetch();
        return replyDtoList;
    }
}
