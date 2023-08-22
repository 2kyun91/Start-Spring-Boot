package com.start.springboot.domain.user.repository;

import com.start.springboot.domain.user.dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> findByIdContains(String userId);
}
