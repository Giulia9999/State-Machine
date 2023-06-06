package com.example.loginsystem.configurations;

import org.springframework.context.annotation.Configuration;

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
