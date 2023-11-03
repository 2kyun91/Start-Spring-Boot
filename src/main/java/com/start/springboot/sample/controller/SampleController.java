package com.start.springboot.sample.controller;

import com.start.springboot.domain.user.dto.UserDto;
import com.start.springboot.domain.user.service.UserService;
import com.start.springboot.sample.dto.Sample;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class SampleController {
    private final UserService userService;

    @GetMapping("/hello")
    public String sayHelloWorld() {
        return "Hello World!";
    }

    @GetMapping("/sayHello")
    public Sample makeSample() {
        Sample svo = new Sample();
        svo.setVal1("안녕?");
        svo.setVal2("만나서");
        svo.setVal3("반가워!!!!");
        return svo;
    }

    @GetMapping("/intro")
    @ResponseBody
    public Map<String, String> intro(@Param("p1") String p1, @Param("p2") String p2) {
        Map map = new HashMap();
        map.put("userId", "testUser");
        map.put("userName", "자바");
        map.put("userEmail", "testUser@gmail.com");
        map.put("p1", p1);
        map.put("p2", p2);
        return map;
    }

    @GetMapping("/sample")
    public String sample2(Model model) {
        List<UserDto> userDtoList = userService.getUserList("testUser");
        model.addAttribute("userDtoList", userDtoList);
        model.addAttribute("msg", "스타트 스프링 부트 - 블로그 만들기");
        return "sample/sample";
    }

    @Secured({"ROLE_USER"})
    @RequestMapping("/userSecret")
    public String forUserSecret() {
        log.info("-------------- USER Secret --------------");
        return "sample/userSecret";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/adminSecret")
    public String forAdminSecret() {
        log.info("-------------- ADMIN Secret --------------");
        return "sample/adminSecret";
    }
}


