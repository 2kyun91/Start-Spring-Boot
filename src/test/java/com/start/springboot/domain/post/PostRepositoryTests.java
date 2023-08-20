package com.start.springboot.domain.post;

import com.querydsl.core.Tuple;
import com.start.springboot.domain.attach.dto.AttachDto;
import com.start.springboot.domain.attach.entity.Attach;
import com.start.springboot.domain.attach.service.AttachService;
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
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.IntStream;

@SpringBootTest
@Import({BoardRepositoryTests.class})
public class PostRepositoryTests {
    private final PostService postService;
    private final AttachService attachService;
    private final BoardRepositoryTests boardRepositoryTests;

    @Autowired
    public PostRepositoryTests(PostService postService, AttachService attachService, BoardRepositoryTests boardRepositoryTests) {
        this.postService = postService;
        this.attachService = attachService;
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
        postDto.setPostTitle("오늘은 8월 셋째주 토요일입니다.");
        postDto.setPostContent("주말이라 집에서 공부 중입니다.");
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
        postService.deletePost(204L);
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

    @Test
    public void testGetPostByTitleAndPostIdGreaterThan2() {
        List<Tuple> result = postService.getPostByTitleAndPostIdGreaterThan2("테스트 게시글 150", 100L);
        if (!result.isEmpty()) {
//            ArrayList arrayList = new ArrayList(Arrays.asList(result.toArray()));
            result.forEach(r -> Arrays.stream(r.toArray()).forEach(System.out::println));
            System.out.println(result.size() + "개의 게시글을 조회하였습니다.");
        } else {
            System.out.println("조회된 게시글이 없습니다.");
        }
    }

    @Test
    public void testCreatePostIncludeAttach() {
        BoardDto boardDto = boardRepositoryTests.testGetBoard(1L);

        PostDto postDto = new PostDto();
        postDto.setPostTitle("오늘은 8월 셋째주 금요일입니다.");
        postDto.setPostContent("독서실에 와서 공부 중입니다.");
        postDto.setPostWriter("자바");
        postDto.setBoard(boardDto.toEntity());

        postDto = postService.createPost(postDto);

        AttachDto attachDto = new AttachDto();
        attachDto.setAttachPath("C:\\Desktop\\img\\testImg.img");
        attachDto.setPost(postDto.toEntity());

        attachService.createAttach(attachDto);
        System.out.println("첨부파일이 포함된 게시글 작성 완료");
    }

    @Test
    @Transactional
    @Commit
    public void testUpdatePostIncludeAttach() {
        Post post = postService.getPost(204L);
        if (!ObjectUtils.isEmpty(post)) {
            post.getAttaches().forEach(v -> {
                AttachDto attachDto = new AttachDto();
                attachDto.setAttachId(v.getAttachId());
                attachDto.setAttachPath("C:\\test\\fakePath");
                attachDto.setPost(post);
                attachService.updateAttach(attachDto);
            });
        }
    }

    @Test
    public void testDeleteAttach() {
        attachService.deleteAttach(3L);
    }

    @Test
    public void testCreateDummyPostIncludeAttach() {
        BoardDto boardDto = boardRepositoryTests.testGetBoard(1L);

        PostDto postDto = new PostDto();
        postDto.setPostTitle("오늘은 8월 셋째주 일요일입니다.");
        postDto.setPostContent("집에서 공부 중입니다.");
        postDto.setPostWriter("자바");
        postDto.setBoard(boardDto.toEntity());
        postDto = postService.createPost(postDto);

        List<Attach> attaches = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            AttachDto attachDto = new AttachDto();
            attachDto.setAttachPath("C:\\Desktop\\img\\testImg" + i + ".img");
            attachDto.setPost(postDto.toEntity());
            attaches.add(attachDto.toEntity());
        }

        attachService.createDummyPostIncludeAttach(attaches);
    }

    @Test // 게시글 제목으로 해당 게시글과 첨부파일 수를 게시글 번호 역순으로 조회
    public void testGetPostWithAttachCountOrderByPostIdDesc() {
        List<Tuple> result = postService.getPostWithAttachCountOrderByPostIdDesc("오늘은");
        System.out.println(result);
    }
}
