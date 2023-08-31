package com.start.springboot.domain.board.repository;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.dto.PostBoardDto;

import java.util.List;

public interface BoardRepositoryCustom {
    public long updateBoard(Board board);

    public String getBoardName(Long boardId);

    public List<PostBoardDto> getAllBoard();
}
