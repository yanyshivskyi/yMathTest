package com.yanyshivskyi.yMathTest.service;

import com.yanyshivskyi.yMathTest.domain.Role;
import com.yanyshivskyi.yMathTest.domain.User;
import com.yanyshivskyi.yMathTest.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public void save(User user) {
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setGroupst(user.getGroupst());
        user.setStfio(user.getStfio());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }
}