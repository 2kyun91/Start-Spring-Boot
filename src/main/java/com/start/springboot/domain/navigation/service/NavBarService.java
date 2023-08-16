package com.start.springboot.domain.navigation.service;

import com.start.springboot.domain.navigation.dto.NavBarDto;
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

    public NavBarDto getNavBar(NavBarDto navBarDto) {
        NavBar navBar = navbarRepository.findByNavBarGnb(navBarDto.getNavBarGnb()).orElseGet(
                () -> navbarRepository.save(createNavBar(navBarDto))
        );
        return navBar.toDto(navBar);
    }

    public NavBar createNavBar(NavBarDto navBarDto) {
        NavBar navBar = navBarDto.toEntity();
        navBar = navbarRepository.save(navBar);
        return navBar;
    }
}
