package com.microfood.rating.amqp;

import com.microfood.rating.dtos.PaymentDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.microfood.rating.amqp.RatingAmqpConfig.QUEUE_NAME;


@Component
public class RatingListener {

    @RabbitListener(queues = QUEUE_NAME)
    public void listen(PaymentDto message) {
        System.out.println(message);
    }
}
