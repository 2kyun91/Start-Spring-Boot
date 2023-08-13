package com.start.springboot.domain.board.service;

import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseGet(() -> null);
        return board;
    }

    public Board createBoard(Board board) {
        boardRepository.save(board);
        return board;
    }
}

