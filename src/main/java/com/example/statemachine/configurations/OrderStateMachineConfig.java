package com.example.statemachine.configurations;

import com.example.statemachine.entities.OrderEvent;
import com.example.statemachine.entities.OrderState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachine
public class OrderStateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderState, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderState, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderState.CREATED)
                .states(EnumSet.allOf(OrderState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderState, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderState.CREATED)
                .target(OrderState.PROCESSING)
                .event(OrderEvent.START_PROCESSING)
                .and()
                .withExternal()
                .source(OrderState.PROCESSING)
                .target(OrderState.COMPLETED)
                .event(OrderEvent.COMPLETE)
                .and()
                .withExternal()
                .source(OrderState.PROCESSING)
                .target(OrderState.CANCELLED)
                .event(OrderEvent.CANCEL);
    }
}
