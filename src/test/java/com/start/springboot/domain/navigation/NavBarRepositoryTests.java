package com.start.springboot.domain.navigation;

import com.start.springboot.domain.navigation.entity.NavBar;
import com.start.springboot.domain.navigation.service.NavBarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootTest
@TestConfiguration
public class NavBarRepositoryTests {
    private final NavBarService navBarService;

    @Autowired
    public NavBarRepositoryTests(NavBarService navBarService) {
        this.navBarService = navBarService;
    }

    @Test
    public NavBar getNavBar(String navBarGnb, String nabBarLnb) {
        NavBar navBar = navBarService.getNavBar(navBarGnb, nabBarLnb);
        return navBar;
    }
}
