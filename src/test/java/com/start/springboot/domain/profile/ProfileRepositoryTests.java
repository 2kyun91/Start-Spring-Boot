package com.start.springboot.domain.profile;

import com.start.springboot.domain.profile.dto.ProfileDto;
import com.start.springboot.domain.profile.service.ProfileService;
import com.start.springboot.domain.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.stream.IntStream;

@SpringBootTest
public class ProfileRepositoryTests {
    private final ProfileService profileService;

    @Autowired
    public ProfileRepositoryTests(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Test
    public void testCreateProfile() {
        UserDto userDto = new UserDto();
        userDto.setUserId("testUser");

        IntStream.range(1, 3).forEach(i -> {
            ProfileDto profileDto = new ProfileDto();
            profileDto.setProfileImgPath("profileImg" + i);
            profileDto.setUser(userDto.toEntity());

            profileService.createProfile(profileDto);
        });

    }

    @Test
    public void testDeleteProfile() {
        profileService.deleteProfile(2L);
    }

    @Test
    public void testUpdateProfile() {

    }
}
