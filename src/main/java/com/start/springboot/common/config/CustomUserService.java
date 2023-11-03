package com.start.springboot.common.config;

import com.start.springboot.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User sampleUser = new User(username, "qwe123!@#", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
//        userRepository.findById(username).ifPresent(user -> {
//            log.info("CustomUserService loadUserByUsername() Active...");
//            log.info("" + user);
//        });
//        return null;

        return userRepository.findById(username)
                .filter(user -> user != null)
                .map(user -> new CustomSecurityUser(user))
                .get();
    }
}
