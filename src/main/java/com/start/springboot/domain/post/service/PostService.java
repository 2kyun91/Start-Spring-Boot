package com.start.springboot.domain.post.service;

import com.querydsl.core.Tuple;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.post.repository.PostRepository;
import com.start.springboot.domain.post.repository.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseGet(() -> null);
//        return postRepository.findByPostId(postId);
    }

    public List<Post> getPostByTitleContaining(String postTitle) {
        return postRepository.findByPostTitleContaining(postTitle);
    }

    public Post writePost(Post post) {
        postRepository.save(post);
        return post;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> getPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        return postRepository.findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(postTitle, postIdGreater, postIdLess, pageable);
    }

    public Page<Post> getPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        return postRepository.findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(postTitle, postIdGreater, postIdLess, pageable);
    }

    public Page<Post> getPostByTitleContainingAndPostIdLessThan(String postTitle, Long postIdLess, Pageable pageable) {
        return postRepository.findByPostTitleContainingAndPostIdLessThan(postTitle, postIdLess, pageable);
    }

    public List<Tuple> getPostByTitleAndPostIdGreaterThan(String postTitle, Long postId) {
        return postRepository.findByPostTitleAndPostIdGreaterThan(postTitle, postId);
    }
}
