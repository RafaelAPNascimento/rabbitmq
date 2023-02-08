package com.example.rabbitmq.consumer;

import com.example.rabbitmq.config.MessagingConfig;
import com.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.rabbitmq.config.MessagingConfig.QUEUE_TEST;

@Component
public class User {

    @RabbitListener(queues = QUEUE_TEST)
    public void consumeFromQueue(OrderStatus orderStatus) {

        System.out.println("message received form queue: " + orderStatus);
    }
}
