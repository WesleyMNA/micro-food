package com.microfood.orders.amqp;

import com.microfood.orders.dtos.PaymentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.microfood.orders.amqp.OrderAmqpConfig.QUEUE_NAME;

@Component
public class OrderListener {

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(PaymentDto message) {
        System.out.println(message);
    }
}
