package com.start.springboot.common.config;

import com.start.springboot.domain.userRole.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomSecurityUser extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    private com.start.springboot.domain.user.entity.User user;

    public CustomSecurityUser(com.start.springboot.domain.user.entity.User user) {
        super(user.getUserId(), user.getUserPwd(), makeGrantedAuthority(user.getUserRoles()));
        this.user = user;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<UserRole> userRoles) {
        List<GrantedAuthority> list = new ArrayList<>();
        userRoles.forEach(userRole -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + userRole.getRoleName())));
        return list;
    }
}
