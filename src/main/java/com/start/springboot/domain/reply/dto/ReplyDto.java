package com.start.springboot.domain.reply.dto;

import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.reply.entity.Reply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ReplyDto {
    private Long replyId;

    private Long replyRelateId;

    private String replyContent;

    private String replyWriter;

    private String replyShowYn;

    private Post post;

    private Timestamp replyCreateDate;

    private Timestamp replyUpdateDate;

    public Reply toEntity() {
        return Reply.builder()
                .replyId(replyId)
                .replyRelateId(replyRelateId)
                .replyContent(replyContent)
                .replyWriter(replyWriter)
                .replyShowYn(replyShowYn)
                .post(post)
                .replyCreateDate(replyCreateDate)
                .replyUpdateDate(replyUpdateDate)
                .build();
    }
}
