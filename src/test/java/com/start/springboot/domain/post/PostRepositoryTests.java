package com.start.springboot.domain.post;

import com.start.springboot.domain.board.BoardRepositoryTests;
import com.start.springboot.domain.board.entity.Board;
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

import java.util.List;

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
        Post post = postService.getPostById(1L);
        if (!ObjectUtils.isEmpty(post)) {
            System.out.println(post);
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
        return post;
    }

    @Test
    public void testWritePost() {
        Board board = boardRepositoryTests.testGetBoard(1L);
        Post post = new Post();
        post.setBoardId(board.getBoardId());
//        post.setPostTitle("오늘의 날씨");
//        post.setPostContent("태풍의 영향으로 습한 날씨입니다.");
//        post.setPostWriter("자바");
        post.setPostTitle("오늘은 8월 둘째주 토요일입니다.");
        post.setPostContent("밖에 나가지 않고 집에서 공부 중입니다.");
        post.setPostWriter("자바");

        postService.writePost(post);
        System.out.println(post);
    }

    @Test
    public void testUpdatePost() {
        Post post = testGetPost();
        if (!ObjectUtils.isEmpty(post)) {
            post.setPostTitle("오늘의 날씨 update");
            postService.writePost(post);
            System.out.println("게시글 수정 완료");
        } else {
            System.out.println("수정할 게시글이 없습니다.");
        }
    }

    @Test
    public void testDeletePost() {
        postService.deletePost(2L);
    }

    @Test
    public void testWriteDummyPost() {
        Board board = boardRepositoryTests.testGetBoard(1L);
        for (int i = 1; i <= 200; i++) {
            Post post = new Post();
            post.setBoardId(board.getBoardId());
            post.setPostTitle("테스트 글 제목 " + i);
            post.setPostContent(RandomStringUtils.randomAlphanumeric(10));
            post.setPostWriter("자바");
            postService.writePost(post);
        }
    }

    @Test
    public void testGetPostByTitleContaining() {
        List<Post> post = postService.getPostByTitleContaining("테스트");
        if (!post.isEmpty()) {
            post.forEach(p -> System.out.println(p));
            System.out.println(post.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Post> post = postService.getPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThanOrderByPostIdDesc("테스트", 0L, 51L, pageable);
        if (!post.isEmpty()) {
            post.forEach(p -> System.out.println(p));
            System.out.println(post.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testGetPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThan() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "postId");
        Page<Post> post = postService.getPostByTitleContainingAndPostIdGreaterThanAndPostIdLessThan("테스트", 0L, 51L, pageable);
        if (!post.isEmpty()) {
            post.forEach(p -> System.out.println(p));
            System.out.println("페이지당 게시글 수 : " + post.getSize());
            System.out.println("총 페이지 수 : " + post.getTotalPages());
            System.out.println("총 게시글 수 : " + post.getTotalElements());
            System.out.println("다음 페이지 : " + post.nextPageable());
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }
}
