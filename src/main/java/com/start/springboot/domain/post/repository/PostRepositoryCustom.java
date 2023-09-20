package com.start.springboot.domain.post.repository;

import com.querydsl.core.Tuple;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.domain.post.dto.PostBoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    long updatePost(Post post);

    List<Post> findByPostTitleContaining(String postTitle);

    List<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);

    Page<PostDto> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);

    Page<PostDto> findByPostTitleContainingAndPostIdLessThan(String postTitle, Long postIdLess, Pageable pageable);

    List<PostDto> findByPostTitleAndPostIdGreaterThan(String postTitle, Long postIdGreater);

    List<Tuple> findByPostTitleAndPostIdGreaterThan2(String postTitle, Long postIdGreater);

    List<Tuple> findByPostWithAttachCountOrderByPostIdDesc(String postTitle);

    Page<PostBoardDto> getPostList(SearchDto searchDto, Pageable pageable);
}
