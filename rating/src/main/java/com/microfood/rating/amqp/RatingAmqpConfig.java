package com.microfood.rating.amqp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RatingAmqpConfig {

    public static final String QUEUE_NAME = "payments.rating-details";
    public static final String EXCHANGE_NAME = "payment.ex";
    public static final String DLX_NAME = "payments.dlx";

    @Bean
    public Queue createQueue() {
        return QueueBuilder
                .nonDurable(QUEUE_NAME)
                .deadLetterExchange(DLX_NAME)
                .build();
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return ExchangeBuilder
                .fanoutExchange(EXCHANGE_NAME)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(createQueue())
                .to(fanoutExchange());
    }

    @Bean
    public FanoutExchange deadLetterExchange() {
        return ExchangeBuilder
                .topicExchange(DLX_NAME)
                .build();
    }

    @Bean
    public Queue createDeadLetterQueue() {
        return QueueBuilder
                .durable("payments.rating-details-dlq")
                .build();
    }

    @Bean
    public Binding bindDeadLetterQueue() {
        return BindingBuilder
                .bind(createDeadLetterQueue())
                .to(deadLetterExchange());
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}
