package com.start.springboot.domain.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
