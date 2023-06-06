package com.example.loginsystem.repositories;

import com.example.loginsystem.entities._User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<_User, Long> {

    _User findByUsername(String username);
}
