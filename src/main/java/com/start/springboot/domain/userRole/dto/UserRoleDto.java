package com.start.springboot.domain.userRole.dto;

import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.userRole.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserRoleDto {
    private Long roleId;

    private String roleName;

    private User user;

    public UserRole toEntity() {
        return UserRole.builder()
                .roleId(roleId)
                .roleName(roleName)
                .user(user)
                .build();
    }
}
