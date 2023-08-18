package com.start.springboot.domain.profile.dto;

import com.start.springboot.domain.profile.entity.Profile;
import com.start.springboot.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ProfileDto {
    private Long profileId;

    private String profileImgPath;

    private String profileShowYn;

    private User user;

    private Timestamp profileCreateDate;

    private Timestamp profileUpdateDate;

    public Profile toEntity() {
        return Profile.builder()
                .profileId(profileId)
                .profileImgPath(profileImgPath)
                .profileShowYn(profileShowYn)
                .user(user)
                .profileCreateDate(profileCreateDate)
                .profileUpdateDate(profileUpdateDate)
                .build();
    }
}
