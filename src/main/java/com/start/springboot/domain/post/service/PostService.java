package com.start.springboot.domain.post.service;

import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseGet(() -> null);
    }

    public PostDto createPost(PostDto postDto) {
        Post post = postRepository.save(postDto.toEntity());
        return post.toDto(post);
    }

    public void updatePost(PostDto postDto) {
        postRepository.updatePost(postDto.toEntity());
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<PostDto> getPostTitleContaining(String postTitle) {
        List<Post> posts = postRepository.findByPostTitleContaining(postTitle);
        List<PostDto> postDtos = new ArrayList<>();
        //Set<PostDto> postDtos = new LinkedHashSet<>();
        if (!posts.isEmpty()) {
            posts.forEach(post -> postDtos.add(post.toDto(post)));
        }
        return postDtos;
    }

    public List<PostDto> getPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        List<Post> posts = postRepository.findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc(postTitle, postIdGreater, postIdLess, pageable);
        List<PostDto> postDtos = new ArrayList<>();
        if (!posts.isEmpty()) {
            posts.forEach(post -> postDtos.add(post.toDto(post)));
        }
        return postDtos;
    }

    public Page<PostDto> getPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(String postTitle, Long postIdGreater, Long postIdLess, Pageable pageable) {
        return postRepository.findByPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan(postTitle, postIdGreater, postIdLess, pageable);
    }

    public Page<PostDto> getPostTitleContainingAndPostIdLessThan(String postTitle, Long postIdLess, Pageable pageable) {
        return postRepository.findByPostTitleContainingAndPostIdLessThan(postTitle, postIdLess, pageable);
    }

    public List<PostDto> getPostByTitleAndPostIdGreaterThan(String postTitle, Long postId) {
        return postRepository.findByPostTitleAndPostIdGreaterThan(postTitle, postId);
    }
}
