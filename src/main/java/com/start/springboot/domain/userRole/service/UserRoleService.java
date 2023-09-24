package com.start.springboot.domain.userRole.service;

import com.start.springboot.domain.userRole.dto.UserRoleDto;
import com.start.springboot.domain.userRole.entity.UserRole;
import com.start.springboot.domain.userRole.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleDto saveUserRole(UserRoleDto userRoleDto) {
        UserRole userRole = userRoleRepository.save(userRoleDto.toEntity());
        return userRole.toDto(userRole);
    }
}
