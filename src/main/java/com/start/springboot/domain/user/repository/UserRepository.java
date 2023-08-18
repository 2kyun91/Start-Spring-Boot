package com.start.springboot.domain.user.repository;

import com.start.springboot.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>, UserRepositoryCustom {
}
