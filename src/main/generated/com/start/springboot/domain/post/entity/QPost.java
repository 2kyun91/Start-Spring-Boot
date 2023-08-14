package com.start.springboot.domain.post.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 2065537009L;

    public static final QPost post = new QPost("post");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath postContent = createString("postContent");

    public final DateTimePath<java.sql.Timestamp> postCreateDate = createDateTime("postCreateDate", java.sql.Timestamp.class);

    public final NumberPath<Long> postId = createNumber("postId", Long.class);

    public final StringPath postShowYN = createString("postShowYN");

    public final StringPath postTitle = createString("postTitle");

    public final DateTimePath<java.sql.Timestamp> postUpdateDate = createDateTime("postUpdateDate", java.sql.Timestamp.class);

    public final NumberPath<Integer> postViewCount = createNumber("postViewCount", Integer.class);

    public final StringPath postWriter = createString("postWriter");

    public QPost(String variable) {
        super(Post.class, forVariable(variable));
    }

    public QPost(Path<? extends Post> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPost(PathMetadata metadata) {
        super(Post.class, metadata);
    }

}

