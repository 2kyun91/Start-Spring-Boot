package com.start.springboot.domain.profile.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProfile is a Querydsl query type for Profile
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProfile extends EntityPathBase<Profile> {

    private static final long serialVersionUID = 1587264183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProfile profile = new QProfile("profile");

    public final DateTimePath<java.sql.Timestamp> profileCreateDate = createDateTime("profileCreateDate", java.sql.Timestamp.class);

    public final NumberPath<Long> profileId = createNumber("profileId", Long.class);

    public final StringPath profileImgPath = createString("profileImgPath");

    public final StringPath profileShowYn = createString("profileShowYn");

    public final DateTimePath<java.sql.Timestamp> profileUpdateDate = createDateTime("profileUpdateDate", java.sql.Timestamp.class);

    public final com.start.springboot.domain.user.entity.QUser user;

    public QProfile(String variable) {
        this(Profile.class, forVariable(variable), INITS);
    }

    public QProfile(Path<? extends Profile> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProfile(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProfile(PathMetadata metadata, PathInits inits) {
        this(Profile.class, metadata, inits);
    }

    public QProfile(Class<? extends Profile> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.start.springboot.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

