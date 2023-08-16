package com.start.springboot.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 2065537009L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.start.springboot.domain.board.entity.QBoard board;

    public final StringPath postContent = createString("postContent");

    public final DateTimePath<java.sql.Timestamp> postCreateDate = createDateTime("postCreateDate", java.sql.Timestamp.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath postShowYn = createString("postShowYn");

    public final StringPath postTitle = createString("postTitle");

    public final DateTimePath<java.sql.Timestamp> postUpdateDate = createDateTime("postUpdateDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> postViewCount = createNumber("postViewCount", Integer.class);

    public final StringPath postWriter = createString("postWriter");

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new com.start.springboot.domain.board.entity.QBoard(forProperty("board"), inits.get("board")) : null;
    }

}

