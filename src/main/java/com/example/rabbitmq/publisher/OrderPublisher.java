package com.example.rabbitmq.publisher;

import com.example.rabbitmq.config.MessagingConfig;
import com.example.rabbitmq.dto.Order;
import com.example.rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.example.rabbitmq.config.MessagingConfig.BINDING_TEST;
import static com.example.rabbitmq.config.MessagingConfig.EXCHANGE_TEST;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, String restaurantName) {

        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed successfully");

        template.convertAndSend(EXCHANGE_TEST, BINDING_TEST, orderStatus);

        return "sucess";
    }
}
