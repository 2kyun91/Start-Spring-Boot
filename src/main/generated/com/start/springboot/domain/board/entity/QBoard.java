package com.start.springboot.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -638626217L;

    public static final QBoard board = new QBoard("board");

    public final NumberPath<Long> board_NavBarId = createNumber("board_NavBarId", Long.class);

    public final DateTimePath<java.sql.Timestamp> boardCreateDate = createDateTime("boardCreateDate", java.sql.Timestamp.class);

    public final StringPath boardCreateUser = createString("boardCreateUser");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardName = createString("boardName");

    public final DateTimePath<java.sql.Timestamp> boardUpdateDate = createDateTime("boardUpdateDate", java.sql.Timestamp.class);

    public final StringPath boardUpdateUser = createString("boardUpdateUser");

    public final StringPath boardUseYN = createString("boardUseYN");

    public QBoard(String variable) {
        super(Board.class, forVariable(variable));
    }

    public QBoard(Path<? extends Board> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBoard(PathMetadata metadata) {
        super(Board.class, metadata);
    }

}

