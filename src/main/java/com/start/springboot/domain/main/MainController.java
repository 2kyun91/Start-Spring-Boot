package com.start.springboot.domain.main;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
//    private final UserService userService;
//
//    @GetMapping("/")
//    @ResponseBody
//    public List<UserDto> callByRestApiTest(Model model) {
//        List<UserDto> userDtoList = userService.getUserList("testUser");
//        Map<String, Object> returnMap = new HashMap<>();
//        returnMap.put("result", userDtoList);
//        return userDtoList;
//    }

    @GetMapping("/")
    public String getMainPage(Model model) {
        return "/home";
    }
}
