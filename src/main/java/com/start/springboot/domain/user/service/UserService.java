package com.start.springboot.domain.user.service;

import com.start.springboot.common.errors.errorcode.CommonErrorCode;
import com.start.springboot.common.errors.exception.CustomException;
import com.start.springboot.domain.profile.entity.Profile;
import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String userId) {
        UserDto userDto = new UserDto();
        User user = userRepository.findById(userId).orElseThrow(() -> new CustomException(CommonErrorCode.RESOURCE_NOT_FOUND));
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setUserEmail(user.getUserEmail());
        userDto.setUserCountCreateDate(user.getUserCountCreateDate());

        if (!user.getProfiles().isEmpty()) {
            List<Profile> profileList = new ArrayList<>();
            user.getProfiles().forEach(profile -> {
                profileList.add(profile);
            });
            userDto.setProfiles(profileList);
        }
        return userDto;
    }

    public List<UserDto> getUserList(String userId) {
        List<UserDto> userDtoList = userRepository.findByIdContains(userId);
        return userDtoList;
    }

    public UserDto createUser(UserDto userDto) {
        User user = userRepository.save(userDto.toEntity());
        return user.toDto(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
