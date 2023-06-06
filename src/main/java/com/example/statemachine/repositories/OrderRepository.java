package com.example.statemachine.repositories;

import com.example.statemachine.entities._Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<_Order, Long> {
}
