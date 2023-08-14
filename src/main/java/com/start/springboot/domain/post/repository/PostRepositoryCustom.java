package com.start.springboot.domain.post.repository;

import com.querydsl.core.Tuple;
import com.start.springboot.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    public List<Post> findByPostTitleContaining(String postTitle);

    public List<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);

    public Page<Post> findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable);

    public Page<Post> findByPostTitleContainingAndPostIdLessThan(String postTitle, Long postIdLess, Pageable pageable);

    public List<Tuple> findByPostTitleAndPostIdGreaterThan(String postTitle, Long postIdGreater);
}
