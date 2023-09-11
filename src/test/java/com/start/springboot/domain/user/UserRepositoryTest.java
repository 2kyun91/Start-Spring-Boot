package com.start.springboot.domain.user;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {
    private final UserService userService;

    @Autowired
    public UserRepositoryTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("testUser");
        userDto.setUserPwd("qwe123!@#");
        userDto.setUserName("자바");
        userDto.setUserEmail("test@gmail.com");
        userDto = userService.createUser(userDto);
    }

    @Test
    public void testCreateDummyUser() {
        IntStream.range(1, 20).forEach(i -> {
            UserDto userDto = new UserDto();
            userDto.setUserId("testUser" + i);
            userDto.setUserPwd("qwe123!@#");
            userDto.setUserName("자바" + i);
            userDto.setUserEmail("test" + i + "@gmail.com");
            userService.createUser(userDto);
        });
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser("testUser");
    }
}
