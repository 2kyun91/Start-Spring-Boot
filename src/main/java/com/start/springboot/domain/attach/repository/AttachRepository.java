package com.start.springboot.domain.attach.repository;

import com.start.springboot.domain.attach.entity.Attach;
import org.springframework.data.repository.CrudRepository;

public interface AttachRepository extends CrudRepository<Attach, Long>, AttachRepositoryCustom {
}
