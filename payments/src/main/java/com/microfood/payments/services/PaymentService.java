package com.microfood.payments.services;

import com.microfood.payments.clients.OrderClient;
import com.microfood.payments.dtos.PaymentDto;
import com.microfood.payments.models.Payment;
import com.microfood.payments.models.Status;
import com.microfood.payments.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.microfood.payments.amqp.PaymentAmqpConfig.QUEUE_NAME;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final OrderClient orderClient;
    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper mapper;

    public Page<PaymentDto> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(payment -> mapper.map(payment, PaymentDto.class));
    }

    public PaymentDto create(PaymentDto dto) {
        Payment payment = mapper.map(dto, Payment.class);
        payment.setStatus(Status.CREATED);
        repository.save(payment);
        PaymentDto response = mapper.map(payment, PaymentDto.class);
        rabbitTemplate.convertAndSend(QUEUE_NAME, response);
        return response;
    }

    public void update(Long id, PaymentDto dto) {
        Payment payment = mapper.map(dto, Payment.class);
        payment.setId(id);
        repository.save(payment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void approvePayment(Long id) {
        Payment payment = repository
                .findById(id)
                .orElseThrow();
        payment.setStatus(Status.CONFIRMED);
        repository.save(payment);
        orderClient.approvePayment(payment.getId());
    }

    public void changeStatus(Long id) {
        Payment payment = repository
                .findById(id)
                .orElseThrow();
        payment.setStatus(Status.CONFIRMED_PENDING_INTEGRATION);
        repository.save(payment);
    }
}
