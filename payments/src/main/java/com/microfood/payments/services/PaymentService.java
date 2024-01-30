package com.microfood.payments.services;

import com.microfood.payments.dtos.PaymentDto;
import com.microfood.payments.models.Payment;
import com.microfood.payments.models.Status;
import com.microfood.payments.repositories.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentRepository repository;
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
        return mapper.map(payment, PaymentDto.class);
    }

    public void update(Long id, PaymentDto dto) {
        Payment payment = mapper.map(dto, Payment.class);
        payment.setId(id);
        repository.save(payment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
