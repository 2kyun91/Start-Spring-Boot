package com.start.springboot.domain.post.dto;

import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.reply.entity.Reply;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

/**
 * 게시판 - 게시글 Dto
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

    private List<Attach> attaches;

    private int attachesCount;

    private List<Reply> replies;

    private int repliesCount;
}
