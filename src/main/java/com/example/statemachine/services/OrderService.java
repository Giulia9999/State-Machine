package com.example.statemachine.services;

import com.example.statemachine.entities.OrderEvent;
import com.example.statemachine.entities.OrderState;
import com.example.statemachine.entities._Order;
import com.example.statemachine.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private StateMachine<OrderState, OrderEvent> stateMachine;
    @Autowired
    OrderRepository orderRepository;

    public _Order startProcessing(_Order order) {
        stateMachine.sendEvent(OrderEvent.START_PROCESSING);
        order.setState(stateMachine.getState().getId());
    return orderRepository.save(order);
    }

    public _Order completeOrder(_Order order) {
        stateMachine.sendEvent(OrderEvent.COMPLETE);
        order.setState(stateMachine.getState().getId());
        return orderRepository.save(order);
    }

    public _Order cancelOrder(_Order order) {
        stateMachine.sendEvent(OrderEvent.CANCEL);
        order.setState(stateMachine.getState().getId());
        return orderRepository.save(order);
    }

    public _Order getOrderById(Long orderId) {
        return orderRepository.getReferenceById(orderId);
    }

    public _Order createOrder(_Order order) {
        return orderRepository.save(order);
    }
}
