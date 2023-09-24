package com.start.springboot.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Slf4j
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .antMatchers("/")
                                        .permitAll()
                                        .antMatchers("/board/**", "/replies/**").hasAnyRole("USER")
//                                        .anyRequest().authenticated()
                                        .and()
                                        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                            } catch (Exception e) {
                                log.error("Error in SecurityFilterChain...");
                            }
                        }
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withUsername("user")
                    .password("user")
                    .roles("USER")
                    .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception {
        return http.requestMatchers(matchers -> matchers.antMatchers( "/resources/**", "/static/**"))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .requestCache(RequestCacheConfigurer::disable)
                .securityContext(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable).build();
    }
}
