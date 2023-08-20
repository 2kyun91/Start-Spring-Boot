package com.start.springboot.domain.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 322950599L;

    public static final QUser user = new QUser("user");

    public final ListPath<com.start.springboot.domain.profile.entity.Profile, com.start.springboot.domain.profile.entity.QProfile> profiles = this.<com.start.springboot.domain.profile.entity.Profile, com.start.springboot.domain.profile.entity.QProfile>createList("profiles", com.start.springboot.domain.profile.entity.Profile.class, com.start.springboot.domain.profile.entity.QProfile.class, PathInits.DIRECT2);

    public final StringPath userBlockYn = createString("userBlockYn");

    public final DateTimePath<java.sql.Timestamp> userCountCreateDate = createDateTime("userCountCreateDate", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> userCountUpdateDate = createDateTime("userCountUpdateDate", java.sql.Timestamp.class);

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userId = createString("userId");

    public final StringPath userName = createString("userName");

    public final StringPath userNickName = createString("userNickName");

    public final StringPath userPhNumber = createString("userPhNumber");

    public final StringPath userPwd = createString("userPwd");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

