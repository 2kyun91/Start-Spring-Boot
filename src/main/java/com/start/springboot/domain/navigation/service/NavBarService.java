package com.start.springboot.domain.navigation.service;

import com.start.springboot.domain.navigation.entity.NavBar;
import com.start.springboot.domain.navigation.repository.NavbarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NavBarService {
    private final NavbarRepository navbarRepository;

    public NavBar getNavBar(String navBarGnb, String nabBarLnb) {
        NavBar navBar = navbarRepository.findByNavBarGnb(navBarGnb).orElseGet(
                () -> navbarRepository.save(createNavBar(navBarGnb, nabBarLnb))
        );
        return navBar;
    }

    public NavBar createNavBar(String navBarGnb, String nabBarLnb) {
        NavBar navBar = new NavBar();
        navBar.setNavBarGnb(navBarGnb);
        if (!nabBarLnb.isEmpty()) {
            navBar.setNabBarLnb(nabBarLnb);
        }
        navbarRepository.save(navBar);
        return navBar;
    }
}
