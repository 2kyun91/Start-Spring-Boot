package com.start.springboot.domain.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }

    @GetMapping("/accessDenied")
    public String accessDenied() {
        return "/login/accessDenied";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/login/logout";
    }
}
