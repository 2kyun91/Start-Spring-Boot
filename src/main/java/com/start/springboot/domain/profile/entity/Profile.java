package com.start.springboot.domain.profile.entity;

import com.start.springboot.domain.profile.dto.ProfileDto;
import com.start.springboot.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tb_profile")
@SequenceGenerator(name = "PROFILE_SEQ_GENERATOR", sequenceName = "profile_seq", initialValue = 1, allocationSize = 1)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_SEQ_GENERATOR")
    private Long profileId; /* 프로필 Id */

    private String profileImgPath; /* 프로필 이미지 경로 */

    @ColumnDefault("'Y'")
    private String profileShowYn; /* 프로필 노출 여부 */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_user_id")
    private User user;

    @CreationTimestamp
    private Timestamp profileCreateDate; /* 프로필 생성일 */

    @UpdateTimestamp
    private Timestamp profileUpdateDate; /* 프로필 수정일 */

    @Builder
    public Profile(Long profileId, String profileImgPath, String profileShowYn, User user, Timestamp profileCreateDate, Timestamp profileUpdateDate) {
        this.profileId = profileId;
        this.profileImgPath = profileImgPath;
        this.profileShowYn = profileShowYn;
        this.user = user;
        this.profileCreateDate = profileCreateDate;
        this.profileUpdateDate = profileUpdateDate;
    }

    public ProfileDto toDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setProfileId(profile.getProfileId());
        profileDto.setProfileImgPath(profile.getProfileImgPath());
        profileDto.setProfileShowYn(profile.getProfileShowYn());
        profileDto.setUser(profile.getUser());
        profileDto.setProfileCreateDate(profile.getProfileCreateDate());
        profileDto.setProfileUpdateDate(profile.getProfileUpdateDate());
        return profileDto;
    }
}
