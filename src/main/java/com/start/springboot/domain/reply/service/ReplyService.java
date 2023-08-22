package com.start.springboot.domain.reply.service;

import com.start.springboot.domain.reply.dto.ReplyDto;
import com.start.springboot.domain.reply.entity.Reply;
import com.start.springboot.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;

    public void createReply(ReplyDto replyDto) {
        replyRepository.save(replyDto.toEntity());
    }

    public Reply getReply(Long replyId) {
        return replyRepository.findById(replyId).orElseGet(null);
    }
}
