package com.start.springboot.domain.user.entity;

import com.start.springboot.domain.profile.entity.Profile;
import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.userRole.entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * 회원 엔티티
 */
@Getter
@ToString
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "tb_user")
public class User {
    @Id
    private String userId; /* 사용자 Id */

    @NotNull
    private String userPwd; /* 사용자 비밀번호(단방향 암호화 필요, 자릿수 제한 필요) */

    @NotNull
    private String userName; /* 사용자 이름 */

    private String userNickName; /* 사용자 별명(자릿수,특수문자 제한 필요) */

    @NotNull
    private String userEmail; /* 사용자 이메일(이메일 형식 확인 필요) */

    private String userPhNumber; /* 사용자 연락처(휴대폰 번호 형식 필요) */

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoles; /* 사용자 역할 구분(스프링 시큐리티 적용 필요) */

    @ColumnDefault("'N'")
    private String userBlockYn; /* 사용자 차단 여부 */

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Profile> profiles;

    @CreationTimestamp
    private Timestamp userCountCreateDate; /* 사용자 생성일 */

    @UpdateTimestamp
    private Timestamp userCountUpdateDate; /* 사용자 수정일 */

    @Builder(toBuilder = true)
    public User(String userId, String userPwd, String userName, String userNickName, String userEmail, String userPhNumber, List<UserRole> userRoles, String userBlockYn, List<Profile> profiles, Timestamp userCountCreateDate, Timestamp userCountUpdateDate) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.userNickName = userNickName;
        this.userEmail = userEmail;
        this.userPhNumber = userPhNumber;
        this.userRoles = userRoles;
        this.userBlockYn = userBlockYn;
        this.profiles = profiles;
        this.userCountCreateDate = userCountCreateDate;
        this.userCountUpdateDate = userCountUpdateDate;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserPwd(user.getUserPwd());
        userDto.setUserName(user.getUserName());
        userDto.setUserNickName(user.getUserNickName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserPhNumber(user.getUserPhNumber());
        userDto.setUserRoles(user.getUserRoles());
        userDto.setUserBlockYn(user.getUserBlockYn());
        userDto.setProfiles(user.getProfiles());
        userDto.setUserCountCreateDate(user.getUserCountCreateDate());
        userDto.setUserCountUpdateDate(user.getUserCountUpdateDate());
        return userDto;
    }
}

