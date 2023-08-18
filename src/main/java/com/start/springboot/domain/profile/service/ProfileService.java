package com.start.springboot.domain.profile.service;

import com.start.springboot.domain.profile.dto.ProfileDto;
import com.start.springboot.domain.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void createProfile(ProfileDto profileDto) {
        profileRepository.save(profileDto.toEntity());
    }

    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }

    public void updateProfile(ProfileDto profileDto) {

    }
}
