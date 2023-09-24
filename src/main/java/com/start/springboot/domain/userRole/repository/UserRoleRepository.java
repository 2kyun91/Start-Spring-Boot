package com.start.springboot.domain.userRole.repository;

import com.start.springboot.domain.userRole.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, String> {
}
