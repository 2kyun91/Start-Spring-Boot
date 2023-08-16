package com.start.springboot.domain.post;

import com.querydsl.core.Tuple;
import com.start.springboot.domain.board.BoardRepositoryTests;
import com.start.springboot.domain.board.dto.BoardDto;
import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.post.service.PostService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@SpringBootTest
@Import({BoardRepositoryTests.class})
public class PostRepositoryTests {
    private final PostService postService;
    private final BoardRepositoryTests boardRepositoryTests;

    @Autowired
    public PostRepositoryTests(PostService postService, BoardRepositoryTests boardRepositoryTests) {
        this.postService = postService;
        this.boardRepositoryTests = boardRepositoryTests;
    }

    @Test
    public void inspect() {
//        Class<?> clz = postRepository.getClass();
//        System.out.println(clz.getName());
//        System.out.println("----------------------------");
//
//        Class<?>[] interfaces = clz.getInterfaces();
//        Stream.of(interfaces).forEach(itf -> System.out.println(itf.getName()));
//        System.out.println("----------------------------");
//
//        Class<?> superClasses = clz.getSuperclass();
//        System.out.println(superClasses.getName());
    }

    @Test
    public Post testGetPost() {
        Post post = postService.getPost(1L);
        if (!ObjectUtils.isEmpty(post)) {
            System.out.println(post);
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
        return post;
    }

    @Test
    public void testCreatePost() {
        BoardDto boardDto = boardRepositoryTests.testGetBoard(1L);

        PostDto postDto = new PostDto();
        postDto.setPostTitle("오늘은 8월 셋째주 수요일입니다.");
        postDto.setPostContent("독서실에 와서 공부 중입니다.");
        postDto.setPostWriter("자바");
        postDto.setBoard(boardDto.toEntity());
        postService.createPost(postDto);
    }

    @Test
    public void testCreateDummyPost() {
        BoardDto boardDto = boardRepositoryTests.testGetBoard(1L);
        for (int i = 1; i <= 200; i++) {
            PostDto postDto = new PostDto();
            postDto.setPostTitle("테스트 게시글 " + i);
            postDto.setPostContent(RandomStringUtils.randomAlphanumeric(10));
            postDto.setPostWriter("자바");
            postDto.setBoard(boardDto.toEntity());
            postService.createPost(postDto);
        }
    }

    @Test
    public void testUpdatePost() {
        //Post post = testGetPost();
        Post post = postService.getPost(1L);
        if (!ObjectUtils.isEmpty(post)) {
            PostDto postDto = new PostDto();
            postDto.setPostId(post.getPostId());
            postDto.setPostTitle("오늘의 날씨");
            postDto.setPostContent("구름이 많아 덥지 않은 날씨입니다.");
            postDto.setPostUpdateDate(new Timestamp(System.currentTimeMillis()));
            postService.updatePost(postDto);
            System.out.println("게시글 수정 완료");
        } else {
            System.out.println("수정할 게시글이 없습니다.");
        }
    }

    @Test
    public void testDeletePost() {
        postService.deletePost(5L);
    }

    @Test
    public void testGetPostTitleContaining() {
        List<PostDto> postDtos = postService.getPostTitleContaining("테스트");
        if (!postDtos.isEmpty()) {
            postDtos.forEach(p -> System.out.println(p.getPostTitle()));
            System.out.println(postDtos.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc() {
        Pageable pageable = PageRequest.of(0, 5);
        List<PostDto> postDtos = postService.getPostTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc("테스트", 0L, 51L, pageable);
        if (!postDtos.isEmpty()) {
            postDtos.forEach(p -> System.out.println(p.getPostTitle()));
            System.out.println(postDtos.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "postId");
        Page<PostDto> postDtos = postService.getPostTitleContainingAndPostIdGreaterThanAndPostIdLessThan("테스트", 0L, 51L, pageable);
        if (!postDtos.isEmpty()) {
            postDtos.forEach(p -> System.out.println(p.getPostTitle()));
            System.out.println("페이지당 게시글 수 : " + postDtos.getSize());
            System.out.println("총 페이지 수 : " + postDtos.getTotalPages());
            System.out.println("총 게시글 수 : " + postDtos.getTotalElements());
            System.out.println("다음 페이지 : " + postDtos.nextPageable());
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostTitleContainingAndPostIdLessThan() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "postId");
        Page<PostDto> postDtos = postService.getPostTitleContainingAndPostIdLessThan("테스트 게시글",51L, pageable);
        if (!postDtos.isEmpty()) {
            postDtos.forEach(p -> System.out.println(p.getPostTitle()));
            System.out.println("페이지당 게시글 수 : " + postDtos.getSize());
            System.out.println("총 페이지 수 : " + postDtos.getTotalPages());
            System.out.println("총 게시글 수 : " + postDtos.getTotalElements());
            System.out.println("다음 페이지 : " + postDtos.nextPageable());
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostByTitleAndPostIdGreaterThan() {
        List<PostDto> postDtos = postService.getPostByTitleAndPostIdGreaterThan("테스트 게시글 150", 100L);
        if (!postDtos.isEmpty()) {
            postDtos.forEach(p -> System.out.println(p));
            System.out.println(postDtos.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }
}
