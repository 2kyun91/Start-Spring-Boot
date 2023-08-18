package com.start.springboot.domain.user.service;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto createUser(UserDto userDto) {
        User user = userDto.toEntity();
        user = userRepository.save(user);
        return user.toDto(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
