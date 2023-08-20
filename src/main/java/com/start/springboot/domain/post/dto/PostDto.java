package com.start.springboot.domain.post.dto;

import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.reply.entity.Reply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class PostDto {
    private Long postId;

    private Board board;

    private List<Attach> attaches;

    private List<Reply> replies;

    private String postTitle;

    private String postContent;

    private String postShowYn;

    private String postWriter;

    private int postViewCount;

    private Timestamp postCreateDate;

    private Timestamp postUpdateDate;

    public Post toEntity() {
        return Post.builder()
                .postId(postId)
                .board(board)
                .attaches(attaches)
                .replies(replies)
                .postTitle(postTitle)
                .postContent(postContent)
                .postShowYn(postShowYn)
                .postWriter(postWriter)
                .postViewCount(postViewCount)
                .postCreateDate(postCreateDate)
                .postUpdateDate(postUpdateDate)
                .build();
    }
}
