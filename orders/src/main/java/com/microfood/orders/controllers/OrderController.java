package com.microfood.orders.controllers;

import com.microfood.orders.dtos.OrderDto;
import com.microfood.orders.dtos.StatusDto;
import com.microfood.orders.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<Page<OrderDto>> findAll(@ParameterObject Pageable pageable) {
        Page<OrderDto> response = service.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto dto,
                                           UriComponentsBuilder builder) {
        OrderDto response = service.create(dto);
        URI uri = builder.path("/v1/orders/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity
                .created(uri)
                .body(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                             @RequestBody @Valid StatusDto dto) {
        service.updateStatus(id, dto);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}/approve-payment")
    public ResponseEntity<Void> approvePayment(@PathVariable Long id) {
        service.approvePayment(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
