package com.start.springboot.domain.reply.repository;

import com.start.springboot.domain.reply.entity.Reply;
import org.springframework.data.repository.CrudRepository;

public interface ReplyRepository extends CrudRepository<Reply, Long>, ReplyRepositoryCustom {
}
