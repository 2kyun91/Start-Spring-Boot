package com.start.springboot.domain.board.service;

import com.start.springboot.domain.board.dto.BoardDto;
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

    public BoardDto getBoardReturnDto(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseGet(() -> null);
        BoardDto boardDto = board.toDto(board);
        return boardDto;
    }

    public Board createBoard(BoardDto boardDto) {
        Board board = boardDto.toEntity();
        board = boardRepository.save(board);
        return board;
    }

    public void updateBoard(BoardDto boardDto) {
        boardRepository.updateBoard(boardDto.toEntity());
    }
}

