package com.start.springboot.domain.post.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * 전체보기 게시판 Dto
 */
@Getter
@Setter
@ToString
public class PostBoardDto {
    private Long postId;

    private String postTitle;

    private String postContent;

    private String postShowYn;

    private String postWriter;

    private int postViewCount;

    private Timestamp postCreateDate;

    private Long boardId;

    private String boardName;

    private int attachesCount;

    private int repliesCount;
}
