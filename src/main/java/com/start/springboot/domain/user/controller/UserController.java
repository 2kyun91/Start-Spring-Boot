package com.start.springboot.domain.user.controller;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.entity.User;
import com.start.springboot.domain.user.service.UserService;
import com.start.springboot.domain.userRole.dto.UserRoleDto;
import com.start.springboot.domain.userRole.entity.UserRole;
import com.start.springboot.domain.userRole.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRoleService userRoleService;

    @GetMapping("/signUp")
    public String signUp() {
        return "/signUp/signUp";
    }

    @PostMapping("/signUp")
    public String signUpPost(UserDto userDto, UserRoleDto userRoleDto, ModelAndView mv) {
        log.info("---------------- User 가입 정보 ----------------");
        String encryptPwd = passwordEncoder.encode(userDto.getUserPwd());
        userDto.setUserPwd(encryptPwd);
        userDto = userService.createUser(userDto);
        log.info("userDto : " + userDto.toString());

        userRoleDto.setUser(userDto.toEntity());
        userRoleDto = userRoleService.saveUserRole(userRoleDto);
        log.info("userRoleDto : " + userRoleDto.toString());
        return "redirect:/";
    }
}
