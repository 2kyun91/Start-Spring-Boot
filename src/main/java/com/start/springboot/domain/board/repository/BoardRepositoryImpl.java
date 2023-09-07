package com.start.springboot.domain.board.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.board.entity.Board;
import com.start.springboot.domain.post.dto.PostBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.start.springboot.domain.board.entity.QBoard.board;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updateBoard(Board boardObj) {
        return jpaQueryFactory.update(board)
                .set(board.boardName, boardObj.getBoardName())
                .set(board.boardUpdateUser, boardObj.getBoardUpdateUser())
                .set(board.boardUpdateDate, boardObj.getBoardUpdateDate())
                .where(board.boardId.eq(boardObj.getBoardId()))
                .execute();
    }

    @Override
    public String getBoardName(Long boardId) {
        String boardName = jpaQueryFactory.select(board.boardName).from(board).where(board.boardId.eq(boardId)).fetchOne();
        return boardName;
    }

    @Override
    public List<BoardDto> getAllBoard() {
        List<BoardDto> boardList =  jpaQueryFactory
                .select(
                        Projections.fields(
                                BoardDto.class,
                                board.boardId,
                                board.boardName
                        )
                ).from(board).where(board.boardUseYn.eq("Y")).fetch();
        return boardList;
    }
}
