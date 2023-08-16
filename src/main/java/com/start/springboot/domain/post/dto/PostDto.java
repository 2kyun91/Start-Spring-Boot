package com.start.springboot.domain.post.dto;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class PostDto {
    private Long postId;

    private Board board;

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
