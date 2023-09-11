package com.start.springboot.domain.reply.repository;

import com.start.springboot.domain.reply.dto.ReplyDto;

import java.util.List;

public interface ReplyRepositoryCustom {
    public List<ReplyDto> getReplies(Long postId);
}
