package com.start.springboot.domain.reply;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.board.service.BoardService;
import com.start.springboot.domain.post.entity.Post;
import com.start.springboot.domain.post.service.PostService;
import com.start.springboot.domain.reply.dto.ReplyDto;
import com.start.springboot.domain.reply.entity.Reply;
import com.start.springboot.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final BoardService boardService;
    private final PostService postService;
    private final ReplyService replyService;

    @GetMapping("/{boardId}/{postId}")
    public Map<String, Object> getReplies(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId) {
        Map<String, Object> returnMap = new HashMap<>();

        // 댓글 페이징 처리하기
        List<ReplyDto> replyDtos = replyService.getReplies(postId);
        returnMap.put("replyDtos", replyDtos);
        return returnMap;
    }

    @PostMapping("/{boardId}/{postId}")
    public Map<String, Object> writeReply(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody ReplyDto replyDto ) {
        Map<String, Object> returnMap = new HashMap<>();
        String boardName = boardService.getBoardName(boardId);

        if (!boardName.equals("전체보기")) {
            Post post = postService.getPostQueryMethod(postId);
            if (post.getBoard().getBoardId().equals(boardId)) {
                replyDto.setPost(post);
                replyService.createReply(replyDto);

                List<ReplyDto> replyDtos = replyService.getReplies(postId);
                returnMap.put("replyDtos", replyDtos);
            }
        } else {
            throw new CustomException(CommonErrorCode.INVALID_PARAMETER);
        }

        return returnMap;
    }

    @DeleteMapping("/{boardId}/{postId}/{replyId}")
    public Map<String, Object> deleteReply(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @PathVariable("replyId") Long replyId) {
        Map<String, Object> returnMap = new HashMap<>();
        Reply reply = replyService.getReply(replyId);

        // 사용자와 댓글 작성자 검증이 되었다는 가정
        if (true) {
            replyService.deleteReply(replyId);

            List<ReplyDto> replyDtos = replyService.getReplies(postId);
            returnMap.put("replyDtos", replyDtos);
        }
        return returnMap;
    }

    @PutMapping("/{boardId}/{postId}")
    public Map<String, Object> modifyReply(
            @PathVariable("boardId") Long boardId,
            @PathVariable("postId") Long postId,
            @RequestBody ReplyDto replyDto) {
        Map<String, Object> returnMap = new HashMap<>();
        Reply reply = replyService.getReply(replyDto.getReplyId());

        // 사용자와 댓글 작성자 검증이 되었다는 가정
        if (true) {
            String modifyReplyContent = replyDto.getReplyContent();
            replyDto = reply.toDto(reply);
            replyDto.setReplyContent(modifyReplyContent);
            replyDto.setReplyUpdateDate(Timestamp.valueOf(LocalDateTime.now()));
            replyService.createReply(replyDto);

            List<ReplyDto> replyDtos = replyService.getReplies(postId);
            returnMap.put("replyDtos", replyDtos);
        }
        return returnMap;
    }
}
