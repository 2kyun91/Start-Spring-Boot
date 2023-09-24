package com.start.springboot.domain.user;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.service.UserService;
import com.start.springboot.domain.userRole.dto.UserRoleDto;
import com.start.springboot.domain.userRole.entity.UserRole;
import com.start.springboot.domain.userRole.service.UserRoleService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {
    private final UserService userService;
    private final UserRoleService userRoleService;
    Logger log = (Logger) LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    public UserRepositoryTest(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setUserId("testUser");
        userDto.setUserPwd("qwe123!@#");
        userDto.setUserName("자바");
        userDto.setUserEmail("test@gmail.com");
        userService.createUser(userDto);
    }

    @Test
    public void testCreateDummyUser() {
        IntStream.range(1, 5).forEach(i -> {
            UserDto userDto = new UserDto();
            userDto.setUserId("testUser" + i);
            userDto.setUserPwd("qwe123!@#");
            userDto.setUserName("자바" + i);
            userDto.setUserEmail("test" + i + "@gmail.com");
            userDto = userService.createUser(userDto);

            UserRoleDto userRoleDto = new UserRoleDto();
            if (i <= 3) {
                userRoleDto.setRoleName("USER");
            } else {
                userRoleDto.setRoleName("ADMIN");
            }
            userRoleDto.setUser(userDto.toEntity());
            userRoleDto = userRoleService.saveUserRole(userRoleDto);
        });
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser("testUser");
    }

    @Test
    public void testGetUser() {
        UserDto userDto = userService.getUser("testUser1");
        log.info(userDto.toString());
    }
}
