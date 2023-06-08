package com.example.statemachine.services;

import com.example.statemachine.entities._User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServiceInterface extends UserDetailsService {
    _User findByUsername(String username);
    _User save(_User user);
    _User findById(Long id);
    void delete(Long id);
}
