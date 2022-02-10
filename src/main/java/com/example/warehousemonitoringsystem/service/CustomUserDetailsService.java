package com.example.warehousemonitoringsystem.service;

import com.example.warehousemonitoringsystem.entity.User;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User myUser = new User(1,"user1","$2a$10$ANqRYwhnCbQShFH5hKzmYO7S02RUQTb/ajk.lbar6A2b34kSmou6q","John");
        if (myUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        } else {
            return new UserDetailsImpl(myUser);
        }
    }
}
