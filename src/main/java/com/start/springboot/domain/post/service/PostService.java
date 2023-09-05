package com.start.springboot.domain.post.service;

import com.querydsl.core.Tuple;
import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.common.search.SearchDto;
import com.start.springboot.domain.post.dto.PostBoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.post.repository.PostRepository;
import com.start.springboot.domain.reply.dto.ReplyDto;
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

    public Post getPostQueryMethod(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
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

    public List<Tuple> getPostByTitleAndPostIdGreaterThan2(String postTitle, Long postId) {
        return postRepository.findByPostTitleAndPostIdGreaterThan2(postTitle, postId);
    }

    public List<Tuple> getPostWithAttachCountOrderByPostIdDesc(String postTitle) {
        return postRepository.findByPostWithAttachCountOrderByPostIdDesc(postTitle);
    }

    public Page<PostBoardDto> getPostList(SearchDto searchDto, Pageable pageable) {
        return postRepository.getPostList(searchDto, pageable);
    }

    public PostBoardDto getPost(Long postId) {
        PostBoardDto postBoardDto = new PostBoardDto();
        Post post = getPostQueryMethod(postId);

        // 게시글 기본
        postBoardDto.setPostTitle(post.getPostTitle());
        postBoardDto.setPostContent(post.getPostContent());
        postBoardDto.setPostWriter(post.getPostWriter());
        postBoardDto.setPostViewCount(post.getPostViewCount());

        // 게시글의 답변
        List<ReplyDto> replyDtos = new ArrayList<>();
        post.getReplies().stream().forEach(reply -> {
            replyDtos.add(reply.toDto(reply));
        });
        postBoardDto.setReplyDtos(replyDtos);
        postBoardDto.setRepliesCount(replyDtos.size());

//        // 게시글의 첨부파일
//        List<AttachDto> attachDtos = new ArrayList<>();
//        post.getAttaches().stream().forEach(attach -> {
//            attachDtos.add(attach.toDto(attach));
//        });
//        postBoardDto.setAttachesCount(attachDtos.size());

        return postBoardDto;
    }
}
