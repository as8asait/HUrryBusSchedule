package com.gradProj.HUrry.Services;

import com.gradProj.HUrry.Repositories.UserRepository;
import com.gradProj.HUrry.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user;
        if (login.contains("@")) {
            user = userRepository.findByEmail(login.trim().toLowerCase());
        } else {
            user = userRepository.findByUsername(login.trim());
        }
        // Handle missing users
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " +  login);
        }

       return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(String.valueOf(user.getRole()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
