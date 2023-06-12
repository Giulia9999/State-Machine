package com.example.statemachine.services;

import com.example.statemachine.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    User findByUsername(String username);
    User save(User user);
    User findById(Long id);
    void delete(Long id);
}
