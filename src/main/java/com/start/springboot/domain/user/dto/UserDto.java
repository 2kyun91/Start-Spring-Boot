package com.start.springboot.domain.user.dto;

import com.start.springboot.domain.profile.entity.Profile;
import com.start.springboot.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString
public class UserDto {
    private String userId;

    private String userPwd;

    private String userName;

    private String userNickName;

    private String userEmail;

    private String userPhNumber;

    // private Role userRole;

    private String userBlockYn;

    private List<Profile> profiles;

    private Timestamp userCountCreateDate;

    private Timestamp userCountUpdateDate;

    public User toEntity() {
        return User.builder()
                .userId(userId)
                .userPwd(userPwd)
                .userName(userName)
                .userNickName(userNickName)
                .userEmail(userEmail)
                .userPhNumber(userPhNumber)
                .userBlockYn(userBlockYn)
                .profiles(profiles)
                .userCountCreateDate(userCountCreateDate)
                .userCountUpdateDate(userCountUpdateDate)
                .build();
    }
}
