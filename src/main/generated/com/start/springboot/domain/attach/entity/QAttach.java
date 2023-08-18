package com.start.springboot.domain.attach.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttach is a Querydsl query type for Attach
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttach extends EntityPathBase<Attach> {

    private static final long serialVersionUID = -683087557L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttach attach = new QAttach("attach");

    public final DateTimePath<java.sql.Timestamp> attachCreateDate = createDateTime("attachCreateDate", java.sql.Timestamp.class);

    public final NumberPath<Long> attachId = createNumber("attachId", Long.class);

    public final StringPath attachPath = createString("attachPath");

    public final StringPath attachShowYn = createString("attachShowYn");

    public final DateTimePath<java.sql.Timestamp> attachUpdateDate = createDateTime("attachUpdateDate", java.sql.Timestamp.class);

    public final com.start.springboot.domain.post.entity.QPost post;

    public QAttach(String variable) {
        this(Attach.class, forVariable(variable), INITS);
    }

    public QAttach(Path<? extends Attach> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttach(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttach(PathMetadata metadata, PathInits inits) {
        this(Attach.class, metadata, inits);
    }

    public QAttach(Class<? extends Attach> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new com.start.springboot.domain.post.entity.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

