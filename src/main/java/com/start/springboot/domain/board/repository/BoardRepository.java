package com.start.springboot.domain.board.repository;

import com.start.springboot.domain.board.entity.Board;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface BoardRepository extends CrudRepository<Board, Long>, BoardRepositoryCustom {
}
