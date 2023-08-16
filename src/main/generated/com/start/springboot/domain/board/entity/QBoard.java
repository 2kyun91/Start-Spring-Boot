package com.start.springboot.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = -638626217L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final DateTimePath<java.sql.Timestamp> boardCreateDate = createDateTime("boardCreateDate", java.sql.Timestamp.class);

    public final StringPath boardCreateUser = createString("boardCreateUser");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath boardName = createString("boardName");

    public final DateTimePath<java.sql.Timestamp> boardUpdateDate = createDateTime("boardUpdateDate", java.sql.Timestamp.class);

    public final StringPath boardUpdateUser = createString("boardUpdateUser");

    public final StringPath boardUseYn = createString("boardUseYn");

    public final com.start.springboot.domain.navigation.entity.QNavBar navBar;

    public final SetPath<com.start.springboot.domain.post.entity.Post, com.start.springboot.domain.post.entity.QPost> posts = this.<com.start.springboot.domain.post.entity.Post, com.start.springboot.domain.post.entity.QPost>createSet("posts", com.start.springboot.domain.post.entity.Post.class, com.start.springboot.domain.post.entity.QPost.class, PathInits.DIRECT2);

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.navBar = inits.isInitialized("navBar") ? new com.start.springboot.domain.navigation.entity.QNavBar(forProperty("navBar")) : null;
    }

}

