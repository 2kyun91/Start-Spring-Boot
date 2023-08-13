package com.start.springboot.domain.navigation.repository;

import com.start.springboot.domain.navigation.entity.NavBar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NavbarRepository extends CrudRepository<NavBar, Long> {
    Optional<NavBar> findByNavBarGnb(String navBarGnb);
}
