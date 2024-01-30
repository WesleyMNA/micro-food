package com.microfood.orders.services;

import com.microfood.orders.dtos.OrderDto;
import com.microfood.orders.dtos.StatusDto;
import com.microfood.orders.models.Order;
import com.microfood.orders.models.Status;
import com.microfood.orders.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository repository;
    private final ModelMapper mapper;

    public Page<OrderDto> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(order -> mapper.map(order, OrderDto.class));
    }

    public OrderDto create(OrderDto dto) {
        Order order = mapper.map(dto, Order.class);
        order.setDateHour(LocalDateTime.now());
        order.setStatus(Status.COMPLETED);
        order.getItens().forEach(item -> item.setOrder(order));
        repository.save(order);
        return mapper.map(order, OrderDto.class);
    }

    public void updateStatus(Long id, StatusDto dto) {
        Order order = repository
                .findById(id)
                .orElseThrow();
        order.setStatus(dto.status());
        repository.save(order);
    }

    public void approvePayment(Long id) {
        Order order = repository
                .findById(id)
                .orElseThrow();
        order.setStatus(Status.PAID);
        repository.save(order);
    }
}
