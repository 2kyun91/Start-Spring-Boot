package com.start.springboot.domain.board.dto;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.navigation.entity.NavBar;
import com.start.springboot.domain.post.entity.Post;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@ToString
public class BoardDto {
    private Long boardId;

    private NavBar navBar;

    private Set<Post> posts;

    private String boardName;

    private String boardUseYn;

    private String boardCreateUser;

    private String boardUpdateUser;

    private Timestamp boardCreateDate;

    private Timestamp boardUpdateDate;

    public Board toEntity() {
        return Board.builder()
                .boardId(boardId)
                .navBar(navBar)
                .posts(posts)
                .boardName(boardName)
                .boardUseYn(boardUseYn)
                .boardCreateUser(boardCreateUser)
                .boardUpdateUser(boardUpdateUser)
                .boardCreateDate(boardCreateDate)
                .boardUpdateDate(boardUpdateDate)
                .build();
    }
}
