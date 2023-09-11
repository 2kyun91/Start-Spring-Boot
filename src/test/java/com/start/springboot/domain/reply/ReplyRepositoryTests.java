package com.start.springboot.domain.reply;

import com.start.springboot.domain.post.dto.PostDto;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.reply.dto.ReplyDto;
import com.start.springboot.domain.reply.service.ReplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.stream.IntStream;

@SpringBootTest
public class ReplyRepositoryTests {
    private final ReplyService replyService;

    @Autowired
    public ReplyRepositoryTests(ReplyService replyService) {
        this.replyService = replyService;
    }

    @Test
    public void testInsertReplies() {
        Long[] tempPostIdArr = {1002L, 1003L}; //
        Arrays.stream(tempPostIdArr).forEach(postId -> {
            PostDto postDto = new PostDto();
            postDto.setPostId(postId);

            IntStream.range(0, 10).forEach(i -> {
                ReplyDto replyDto = new ReplyDto();
                replyDto.setReplyWriter("방문객 " + i);
                replyDto.setReplyContent("잘 보고갑니다 " + i);
                replyDto.setPost(postDto.toEntity());

                replyService.createReply(replyDto);
            });
        });
    }
}
