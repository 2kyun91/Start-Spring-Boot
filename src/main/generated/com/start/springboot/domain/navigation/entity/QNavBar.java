package com.start.springboot.domain.navigation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QNavBar is a Querydsl query type for NavBar
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNavBar extends EntityPathBase<NavBar> {

    private static final long serialVersionUID = 756554357L;

    public static final QNavBar navBar = new QNavBar("navBar");

    public final SetPath<com.start.springboot.domain.board.entity.Board, com.start.springboot.domain.board.entity.QBoard> boards = this.<com.start.springboot.domain.board.entity.Board, com.start.springboot.domain.board.entity.QBoard>createSet("boards", com.start.springboot.domain.board.entity.Board.class, com.start.springboot.domain.board.entity.QBoard.class, PathInits.DIRECT2);

    public final StringPath navBarGnb = createString("navBarGnb");

    public final NumberPath<Long> navBarId = createNumber("navBarId", Long.class);

    public final StringPath navBarLnb = createString("navBarLnb");

    public QNavBar(String variable) {
        super(NavBar.class, forVariable(variable));
    }

    public QNavBar(Path<? extends NavBar> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNavBar(PathMetadata metadata) {
        super(NavBar.class, metadata);
    }

}

