package com.start.springboot.domain.reply.service;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.reply.dto.ReplyDto;
import com.start.springboot.domain.reply.entity.Reply;
import com.start.springboot.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public void createReply(ReplyDto replyDto) {
        replyRepository.save(replyDto.toEntity());
    }

    public Reply getReply(Long replyId) {
        return replyRepository.findById(replyId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
    }

    public List<ReplyDto> getReplies(Long postId) {
        List<ReplyDto> replyDtos = replyRepository.getReplies(postId);
        return replyDtos;
    }

    public void deleteReply(Long replyId) {
        replyRepository.deleteById(replyId);
    }

    public void deleteReplyByPostId(Long postId) {
        replyRepository.deleteReplyByPostId(postId);
    }
}
