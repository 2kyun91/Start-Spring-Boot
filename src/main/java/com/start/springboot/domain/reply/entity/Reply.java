package com.start.springboot.domain.reply.entity;

import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.reply.dto.ReplyDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 댓글 엔티티
 */
@Getter
@ToString
@Entity
@Table(name = "tb_reply")
@DynamicInsert
@NoArgsConstructor
@SequenceGenerator(name = "REPLY_SEQ_GENERATOR", sequenceName = "reply_seq", initialValue = 1, allocationSize = 1)
public class Reply {
    @Id
    @GeneratedValue(generator = "REPLY_SEQ_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long replyId;

    private String replyContent;

    @NotNull
    private String replyWriter;

    @ColumnDefault("'Y'")
    private String replyShowYn;

    @ManyToOne
    @JoinColumn(name = "reply_post_id")
    private Post post;

    @CreationTimestamp
    private Timestamp replyCreateDate;

    @UpdateTimestamp
    private Timestamp replyUpdateDate;

    @Builder
    public Reply(Long replyId, String replyContent, String replyWriter, String replyShowYn, Post post, Timestamp replyCreateDate, Timestamp replyUpdateDate) {
        this.replyId = replyId;
        this.replyContent = replyContent;
        this.replyWriter = replyWriter;
        this.replyShowYn = replyShowYn;
        this.post = post;
        this.replyCreateDate = replyCreateDate;
        this.replyUpdateDate = replyUpdateDate;
    }

    public ReplyDto toDto(Reply reply) {
        ReplyDto replyDto = new ReplyDto();
        replyDto.setReplyId(reply.getReplyId());
        replyDto.setReplyContent(reply.getReplyContent());
        replyDto.setReplyWriter(reply.getReplyWriter());
        replyDto.setReplyShowYn(reply.getReplyShowYn());
        replyDto.setPost(reply.getPost());
        replyDto.setReplyCreateDate(reply.getReplyCreateDate());
        replyDto.setReplyUpdateDate(reply.getReplyUpdateDate());
        return replyDto;
    }
}
