package com.start.springboot.domain.userRole.entity;

import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.userRole.dto.UserRoleDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 회원 권한 엔티티
 */
@Getter
@ToString
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "tb_user_role")
@SequenceGenerator(name = "USER_ROLE_SEQ_GENERATOR", sequenceName = "user_role_seq", initialValue = 1, allocationSize = 1)
public class UserRole {
    @Id
    @GeneratedValue(generator = "USER_ROLE_SEQ_GENERATOR", strategy = GenerationType.SEQUENCE)
    private Long roleId;

    @ColumnDefault("'USER'")
    private String roleName;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_user_id")
    private User user;

    @Builder(toBuilder = true)
    public UserRole(Long roleId, String roleName, User user) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.user = user;
    }

    public UserRoleDto toDto(UserRole userRole) {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setRoleId(userRole.getRoleId());
        userRoleDto.setRoleName(userRole.getRoleName());
        userRoleDto.setUser(userRole.getUser());
        return userRoleDto;
    }
}
