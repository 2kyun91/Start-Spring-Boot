package com.start.springboot.domain.user.service;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto getUser(String userId) {
        UserDto userDto = new UserDto();
        User user = userRepository.findById(userId).orElseGet(null);
        if (!ObjectUtils.isEmpty(user)) {
            userDto.setUserId(user.getUserId());
            userDto.setUserName(user.getUserName());
            userDto.setUserEmail(user.getUserEmail());
            userDto.setUserCountCreateDate(user.getUserCountCreateDate());
        }
        return userDto;
    }

    public List<UserDto> getUserList(String userId) {
        List<UserDto> userDtoList = userRepository.findByIdContains(userId);
        return userDtoList;
    }

    public UserDto createUser(UserDto userDto) {
        User user = userDto.toEntity();
        user = userRepository.save(user);
        return user.toDto(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
