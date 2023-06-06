package com.example.statemachine.controllers;

import com.example.statemachine.entities._Order;
import com.example.statemachine.services.OrderService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<_Order> createOrder(@RequestBody _Order order) {
        orderService.createOrder(order);
        orderService.startProcessing(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<_Order> completeOrder(@PathVariable("orderId") Long orderId) {
        _Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        orderService.completeOrder(order);

        return ResponseEntity.ok(order);
    }

    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<_Order> cancelOrder(@PathVariable("orderId") Long orderId) {
        _Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        orderService.cancelOrder(order);

        return ResponseEntity.ok(order);
    }

}
