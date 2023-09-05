package com.start.springboot.domain.board.service;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.board.repository.BoardRepository;
import com.start.springboot.domain.post.dto.PostBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Board getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
        return board;
    }

    public String getBoardName(Long boardId) {
        return boardRepository.getBoardName(boardId);
    }

    public List<PostBoardDto> getAllBoard() {
        return boardRepository.getAllBoard();
    }

    public BoardDto getBoardReturnDto(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
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

