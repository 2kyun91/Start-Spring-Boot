package com.start.springboot.domain.board.repository;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.dto.PostBoardDto;

import java.util.List;

public interface BoardRepositoryCustom {
    long updateBoard(Board board);

    String getBoardName(Long boardId);

    List<PostBoardDto> getAllBoard();
}
