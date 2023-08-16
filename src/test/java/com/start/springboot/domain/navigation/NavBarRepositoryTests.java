package com.start.springboot.domain.navigation;

import com.start.springboot.domain.navigation.dto.NavBarDto;
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
    public NavBarDto testGetNavBar(NavBarDto navBarDto) {
        navBarDto = navBarService.getNavBar(navBarDto);
        return navBarDto;
    }
}
