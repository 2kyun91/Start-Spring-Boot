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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTest {
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;
    Logger log = (Logger) LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    public UserRepositoryTest(UserService userService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
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

    @Test
    public void testSample() {
        List<Integer> values = Arrays.asList(7, 5, 1, 25, 47, 33, 110, 95, 5, 25);

        List<Integer> result = values.stream()
                .filter(numb -> numb < 100)
                .distinct()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
        System.out.println(result);

        String[] arr = new String[]{};
        // ArrayList로 변환
        ArrayList<String> arrayList = new ArrayList<>();

        // "가", "나", "다"를 ArrayList에 추가
        arrayList.add("가");
        arrayList.add("나");
        arrayList.add("다");

        // ArrayList를 배열로 변환
        arr = arrayList.toArray(new String[0]);
        for (String str : arr) {
            System.out.println(str + " ");
        }
    }
}
