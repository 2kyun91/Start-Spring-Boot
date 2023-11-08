package com.start.springboot.common.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.RequestCacheConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserService customUserService;
    private final DataSource dataSource;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//      String query1 = "select user_id as username, user_pwd as password, 'true' as enabled from tb_user where user_id = ?"; // 1-1
//		String query2 = "select role_user_id as user_id, role_name as role from tb_user_role where role_user_id = ?"; // 1-2
//        auth.jdbcAuthentication()
//        .dataSource(dataSource)
//        .usersByUsernameQuery(query1)
//        .rolePrefix("ROLE_")
//        .authoritiesByUsernameQuery(query2);
        log.info("--------- Build Auth Global ---------");
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails user =
//                User.withUsername("user")
//                    .password(passwordEncoder.encode("user"))
//                    .roles("USER")
//                    .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .antMatchers("/", "/login")
                                        .permitAll()
                                        .antMatchers("/board/**", "/replies/**").hasRole("USER")
                                        .and()
                                        .rememberMe().key("encryptSample")
                                        .tokenRepository(getJDBCRepository())
                                        .tokenValiditySeconds(60*60*24)
//                                        .antMatchers("/board/**", "/replies/**").hasRole("MANAGER");
//                                        .anyRequest().authenticated();
                                        .and()
//                                        .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                                        .csrf().disable();
                            } catch (Exception e) {
                                log.error("Error in SecurityFilterChain...");
                            }
                        }
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(
                        (logout) -> logout
                                .permitAll()
                                .logoutUrl("/logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID", "remember-me")
                )
                .userDetailsService(customUserService)
                .exceptionHandling().accessDeniedPage("/accessDenied");

        return http.build();
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

    private PersistentTokenRepository getJDBCRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
