package com.start.springboot.domain.post.repository;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.start.springboot.domain.post.dto.PostAllBoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.start.springboot.domain.post.entity.QPost.post;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public long updatePost(Post postObj) {
        return jpaQueryFactory.update(post)
                .set(post.postTitle, postObj.getPostTitle())
                .set(post.postContent, postObj.getPostContent())
                .set(post.postUpdateDate, postObj.getPostUpdateDate())
                .where(post.postId.eq(postObj.getPostId()))
                .execute();
    }

    @Override /* select 칼럼에 엔티티 자제하기, 지금은 테스트를 위해 사용 */
    public List<Post> findByPostTitleContaining(String postTitle) {
        return jpaQueryFactory.select(post)
                .from(post)
                .where(post.postTitle.contains(postTitle))
                .fetch();
    }

    @Override
    public List<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        return jpaQueryFactory.select(post)
                .from(post)
                .where(post.postTitle.contains(postTitle), post.postId.gt(postIdGreater), post.postId.lt(postIdLess))
                .orderBy(post.postId.desc())
                .fetch();
    }

    @Override
    public Page<PostDto> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        List<Post> postList = jpaQueryFactory.selectFrom(post)
                .where(post.postTitle.contains(postTitle), post.postId.gt(postIdGreater), post.postId.lt(postIdLess))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.postId.desc())
                .fetch();

        List<PostDto> postDtoList = new ArrayList<>();
        if (!postList.isEmpty()) {
            postList.forEach(p -> postDtoList.add(p.toDto(p)));
        }

        JPAQuery<Long> postListCountQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.postTitle.contains(postTitle), post.postId.gt(postIdGreater), post.postId.lt(postIdLess));

        return PageableExecutionUtils.getPage(postDtoList, pageable, postListCountQuery::fetchOne);
    }

    @Override
    public Page<PostDto> findByPostTitleContainingAndPostIdLessThan(String postTitle, Long postIdLess, Pageable pageable) {
        List<Post> postList = jpaQueryFactory.selectFrom(post)
                .where(post.postTitle.contains(postTitle), post.postId.lt(postIdLess))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.postId.desc())
                .fetch();

        List<PostDto> postDtoList = new ArrayList<>();
        if (!postList.isEmpty()) {
            postList.forEach(p -> postDtoList.add(p.toDto(p)));
        }

        JPAQuery<Long> postListCountQuery = jpaQueryFactory.select(post.count())
                .from(post)
                .where(post.postTitle.contains(postTitle), post.postId.lt(postIdLess));
        return PageableExecutionUtils.getPage(postDtoList, pageable, postListCountQuery::fetchOne);
    }

    @Override
    public List<PostDto> findByPostTitleAndPostIdGreaterThan(String postTitle, Long postIdGreater) {
        return jpaQueryFactory
                .select(Projections.fields(PostDto.class, post.postTitle, post.postWriter, post.postContent, post.postCreateDate))
                .from(post)
                .where(post.postTitle.eq(postTitle), post.postId.gt(postIdGreater))
                .fetch();
    }

    @Override
    public List<Tuple> findByPostTitleAndPostIdGreaterThan2(String postTitle, Long postIdGreater) {
        return jpaQueryFactory
                .select(post.postTitle, post.postWriter, post.postContent, post.postCreateDate)
                .from(post)
                .where(post.postTitle.eq(postTitle), post.postId.gt(postIdGreater))
                .fetch();
    }

    @Override
    public List<Tuple> findByPostWithAttachCountOrderByPostIdDesc(String postTitle) {
        return jpaQueryFactory
                .select(
                        post.postTitle,
                        ExpressionUtils.as(
//                            JPAExpressions.select(post.attaches.size()).from(post),
                                post.attaches.size(),
                            "attachCount"
                        )
                ).from(post)
                .where(post.postTitle.contains(postTitle))
                .orderBy(post.postId.desc()).fetch();
    }

    @Override
    public Page<PostAllBoardDto> getPostByAllBoard(Pageable pageable) {
        List<PostAllBoardDto> postAllBoardDtoList = jpaQueryFactory
                .select(
                        Projections.fields(
                                PostAllBoardDto.class,
                                post.postId,
                                post.postTitle,
                                post.postContent,
                                post.postShowYn,
                                post.postWriter,
                                post.postViewCount,
                                post.postCreateDate,
                                post.board.boardId,
                                post.board.boardName,
                                ExpressionUtils.as(
                                        post.attaches.size(), "attachesCount"
                                ),
                                ExpressionUtils.as(
                                        post.replies.size(), "repliesCount"
                                )
                        )
                ).from(post)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(post.postCreateDate.desc())
                .fetch();

        JPAQuery<Long> postListCountQuery = jpaQueryFactory
                .select(post.count())
                .from(post);

        return PageableExecutionUtils.getPage(postAllBoardDtoList, pageable, postListCountQuery::fetchOne);
    }
}

